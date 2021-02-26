package org.upgrad.upstac.testrequests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.web.server.ResponseStatusException;
import org.upgrad.upstac.config.security.UserLoggedInService;
import org.upgrad.upstac.testrequests.TestRequest;
import org.upgrad.upstac.testrequests.consultation.ConsultationController;
import org.upgrad.upstac.testrequests.consultation.CreateConsultationRequest;
import org.upgrad.upstac.testrequests.lab.TestStatus;
import org.upgrad.upstac.testrequests.RequestStatus;
import org.upgrad.upstac.testrequests.TestRequestQueryService;
import org.upgrad.upstac.users.User;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@SpringBootTest
@Slf4j
@ExtendWith(MockitoExtension.class)
class ConsultationControllerTest {


    @InjectMocks
    ConsultationController consultationController;

    @Mock
    TestRequestUpdateService testRequestUpdateService;

    @Mock
    TestRequestRepository testRequestRepository;


    @Autowired
    TestRequestQueryService testRequestQueryService;

    @Mock
    UserLoggedInService userLoggedInService;


    @Test
    @WithUserDetails(value = "doctor")
    public void calling_assignForConsultation_with_valid_test_request_id_should_update_the_request_status_1(){

        // Arrange
        String emailUser = "email-test@domain.com";
        Long id = 1L;
        User loggedUser = new User();
        loggedUser.setEmail(emailUser);

        TestRequest expectedResponseTestRequest = new TestRequest();
        expectedResponseTestRequest.setRequestId(id);
        expectedResponseTestRequest.setStatus(RequestStatus.DIAGNOSIS_IN_PROCESS);
        expectedResponseTestRequest.setEmail(emailUser);

        // Returns my logged user
        when(userLoggedInService.getLoggedInUser()).thenReturn(loggedUser);

        // Force returns the expected Response
        when(testRequestUpdateService.assignForConsultation(id, loggedUser)).thenReturn(expectedResponseTestRequest);


        // Act
        TestRequest response = this.consultationController.assignForConsultation(id);


        // Assert
        assertThat(response).isNotNull();
        assertThat((String) response.getEmail()).isEqualTo(emailUser);
        assertThat(response.getRequestId()).isEqualTo(id);
        assertThat(response.getStatus()).isEqualTo(RequestStatus.DIAGNOSIS_IN_PROCESS);

    }


    @Test
    @WithUserDetails(value = "doctor")
    public void calling_assignForConsultation_with_valid_test_request_id_should_update_the_request_status_2(){

        // Arrange
        String emailUser = "email-test@domain.com";
        Long id = 1L;
        User loggedUser = new User();
        loggedUser.setEmail(emailUser);

        when(userLoggedInService.getLoggedInUser()).thenReturn(loggedUser);

        // Act
        this.consultationController.assignForConsultation(id);

        // Assert
        verify(testRequestUpdateService,times(1)).assignForConsultation(id, loggedUser);

    }


    public TestRequest getTestRequestByStatus(RequestStatus status) {
        return testRequestQueryService.findBy(status).stream().findFirst().get();
    }

    @Test
    @WithUserDetails(value = "doctor")
    public void calling_assignForConsultation_with_valid_test_request_id_should_throw_exception(){

        Long InvalidRequestId= -34L;

        // TODO
        //Implement this method


        // Create an object of ResponseStatusException . Use assertThrows() method and pass assignForConsultation() method
        // of consultationController with InvalidRequestId as Id


        //Use assertThat() method to perform the following comparison
        //  the exception message should be contain the string "Invalid ID"

    }

    @Test
    @WithUserDetails(value = "doctor")
    public void calling_updateConsultation_with_valid_test_request_id_should_update_the_request_status_and_update_consultation_details(){

        TestRequest testRequest = getTestRequestByStatus(RequestStatus.DIAGNOSIS_IN_PROCESS);

        // TODO
        //Implement this method
        //Create an object of CreateConsultationRequest and call getCreateConsultationRequest() to create the object. Pass the above created object as the parameter

        //Create another object of the TestRequest method and explicitly update the status of this object
        // to be 'COMPLETED'. Make use of updateConsultation() method from labRequestController class (Pass the previously created two objects as parameters)

        //Use assertThat() methods to perform the following three comparisons
        //  1. the request ids of both the objects created should be same
        //  2. the status of the second object should be equal to 'COMPLETED'
        // 3. the suggestion of both the objects created should be same. Make use of getSuggestion() method to get the results.



    }


    @Test
    @WithUserDetails(value = "doctor")
    public void calling_updateConsultation_with_invalid_test_request_id_should_throw_exception(){

        TestRequest testRequest = getTestRequestByStatus(RequestStatus.DIAGNOSIS_IN_PROCESS);

        // TODO
        //Implement this method

        //Create an object of CreateConsultationRequest and call getCreateConsultationRequest() to create the object. Pass the above created object as the parameter

        // Create an object of ResponseStatusException . Use assertThrows() method and pass updateConsultation() method
        // of consultationController with a negative long value as Id and the above created object as second parameter
        //Refer to the TestRequestControllerTest to check how to use assertThrows() method


        //Use assertThat() method to perform the following comparison
        //  the exception message should be contain the string "Invalid ID"

    }

    @Test
    @WithUserDetails(value = "doctor")
    public void calling_updateConsultation_with_invalid_empty_status_should_throw_exception(){

        TestRequest testRequest = getTestRequestByStatus(RequestStatus.DIAGNOSIS_IN_PROCESS);

        // TODO
        //Implement this method

        //Create an object of CreateConsultationRequest and call getCreateConsultationRequest() to create the object. Pass the above created object as the parameter
        // Set the suggestion of the above created object to null.

        // Create an object of ResponseStatusException . Use assertThrows() method and pass updateConsultation() method
        // of consultationController with request Id of the testRequest object and the above created object as second parameter
        //Refer to the TestRequestControllerTest to check how to use assertThrows() method


    }

    public CreateConsultationRequest getCreateConsultationRequest(TestRequest testRequest) {

        // TODO
        //Create an object of CreateLabResult and set all the values
        // if the lab result test status is Positive, set the doctor suggestion as "HOME_QUARANTINE" and comments accordingly
        // else if the lab result status is Negative, set the doctor suggestion as "NO_ISSUES" and comments as "Ok"
        // Return the object


        return null; // Replace this line with your code

    }

}