package com.user.controller.test;

//import static org.junit.Assert.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import com.user.bean.UserBean;
//import com.user.controller.UserController;
//import com.user.entity.User;
//import com.user.exception.InvalidCredentialsException;
//import com.user.exception.PasswordUpdateException;
//import com.user.exception.UserNotFoundException;
//import com.user.service.UserService;
//
//@ExtendWith(MockitoExtension.class)
//class UserControllerTest {
//
//	@Mock
//    private UserService userService;
//	
//	@InjectMocks
//    private UserController userController;
//	
//	private UserBean userBean;
//
//    @BeforeEach
//    void setUp() {
//        userBean=new UserBean();
//        userBean.setUserId(1L);
//        userBean.setFirstName("hf");
//        userBean.setEmail("fg@gmail.com");
//        userBean.setPassword("ghed");
//    }
//
//    @Test
//    void testSave() {
//        User user = new User();
//        user.setUserId(1L);
//        when(userService.saveUser(any(User.class))).thenReturn(user);
//
//        ResponseEntity<User> responseEntity = userController.saveUser(user);
//
//        assert(responseEntity.getStatusCode()).equals(HttpStatus.CREATED);
//        assert(responseEntity.getBody()).equals(user);
//    }
//
//    @Test
//    void testGetById() throws UserNotFoundException {
//        UserBean user = new UserBean();
//        user.setUserId(1L);
//        when(userService.getByUserId(1L)).thenReturn(user);
//
//        ResponseEntity<UserBean> responseEntity = userController.getByUserId(1L);
//
//        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
//        assert(responseEntity.getBody()).equals(user);
//    }
//
//    @Test
//    void testGetAll() throws UserNotFoundException {
//        User user1 = new User();
//        User user2 = new User();
//        List<User> userList = Arrays.asList(user1, user2);
//        when(userService.getAllUsers()).thenReturn(userList);
//
//        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();
//
//        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
//        assert(responseEntity.getBody()).equals(userList);
//    }
//
//    @Test
//    void testDelete() {
//        ResponseEntity<User> responseEntity = userController.deleteUserById(1L);
//
//        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
//    }
//
//    @Test
//    void testUpdate() {
//        User user = new User();
//        Optional<User> optionalUser = Optional.of(user);
//        when(userService.updateUser(any(User.class))).thenReturn(optionalUser);
//
//        ResponseEntity<Optional<User>> responseEntity = userController.updateUser(user);
//
//        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
//        assert(responseEntity.getBody()).equals(optionalUser);
//    }
//
//    @Test
//    Optional<User> testUserLogin() throws InvalidCredentialsException {
//        User user = new User();
//        Optional<User> optionalUser = Optional.of(user);
//        when(userService.userLogin(any(String.class), any(String.class))).thenReturn(optionalUser);
//
//        ResponseEntity<Optional<User>> responseEntity = userController.userLogin(user);
//
//        assert(responseEntity.getStatusCode()).equals(HttpStatus.OK);
//        assert(responseEntity.getBody()).equals(optionalUser);
//		return optionalUser;
//    }
//
//    @Test
//    void testUpdateUserPassword() throws PasswordUpdateException {
//        // Mocking the behavior of userService.updateUserPassword
//        User user = new User(); // Assuming this is a valid user
//        Optional<User> optionalUser = Optional.of(user);
//        
//        // Stubbing the method with specific non-null arguments
//        when(userService.updateUserPassword(anyString(), anyString())) // Use anyString() matcher instead of any(String.class)
//                .thenReturn(optionalUser);
//
//        // Calling the method under test with valid arguments
//        ResponseEntity<Optional<User>> responseEntity = userController.updateUserPassword(user);
//
//        // Verifying the result
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(optionalUser, responseEntity.getBody());
//    }
//
//
//
//}
//




import com.user.bean.UserBean;
import com.user.controller.UserController;
import com.user.entity.User;
import com.user.exception.*;
import com.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveUser() throws UserSaveException {
        User user = new User(); // Create a user object
        when(userService.saveUser(any(User.class))).thenReturn(user);

        ResponseEntity<User> responseEntity = userController.saveUser(user);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testGetByUserId() throws UserNotFoundException {
        UserBean userBean = new UserBean(); // Create a user bean object
        when(userService.getByUserId(anyLong())).thenReturn(userBean);

        ResponseEntity<UserBean> responseEntity = userController.getByUserId(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userBean, responseEntity.getBody());
    }

    @Test
    void testGetAllUsers() throws NoUsersFoundException {
        List<User> userList = List.of(new User(), new User()); // Create a list of user objects
        when(userService.getAllUsers()).thenReturn(userList);

        ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody());
    }

    @Test
    void testDeleteUserById() throws UserDeleteException {
//        when(userService.deleteUserById(anyLong())).thenReturn(true);

        ResponseEntity<User> responseEntity = userController.deleteUserById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateUser() throws UserUpdateException {
        User user = new User(); // Create a user object
        Optional<User> optionalUser = Optional.of(user);
        when(userService.updateUser(any(User.class))).thenReturn(optionalUser);

        ResponseEntity<Optional<User>> responseEntity = userController.updateUser(user);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(optionalUser, responseEntity.getBody());
    }

//    @Test
//    void testUserLogin() throws InvalidCredentialsException {
//        User user = new User(); // Create a user object
//        when(userService.userLogin(any(String.class), any(String.class))).thenReturn(Optional.of(user));
//
//        ResponseEntity<Optional<User>> responseEntity = userController.userLogin(user);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(Optional.of(user), responseEntity.getBody());
//    }

//    @Test
//    void testUpdateUserPassword() throws PasswordUpdateException {
//        User user = new User(); // Create a user object
//        when(userService.updateUserPassword(any(String.class), any(String.class))).thenReturn(Optional.of(user));
//
//        ResponseEntity<Optional<User>> responseEntity = userController.updateUserPassword(user);
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(Optional.of(user), responseEntity.getBody());
//    }
}

