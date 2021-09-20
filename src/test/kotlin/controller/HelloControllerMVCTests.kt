package es.unizar.webeng.hello.controller

//import libraries (junit used for testing, spring for web app).
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
//import also mocking libraries used to create useful mocks for testing
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

//Annotation used to apply only configuration relevant to MVC test.
@WebMvcTest(HelloController::class)

class HelloControllerMVCTests {
    /**
     * Assigns the property `app.message` in `application.properties` to [message]
     */ 

    //We use this annotation to inicialize the properties.
    //We take advantage of Spring´s expression language to access the values
    //using ${value}.

    //A variable (message) of type String is initalized to be able to store the values of the test.
    //we want to prove
    @Value("\${app.message}") private lateinit var message: String

    /**
     * Mocks the Spring controller
     */

    //Spring can enable dependency injection in an automatic mode. This can be 
    //done thanks to this AutoWired annotation. (Great option if you want to mantain the
    //code because it updates automatically).
    @Autowired private lateinit var mockMvc: MockMvc

    /**
     * With the controller [HelloController] mocked, test performs a GET request to server-side
     * endpoint "/" and:
     * 
     * - print the response
     * - expect to receive an OK status (code 200)
     * - expect the atributte "message" of the model to be [message]
     */

     //Annotation @Test is used to specify the body of a unit test.

    @Test
    fun testMessage() {
        //Method .perfom executes a get operation to the URL specified. In this 
        //case, to the root dir "/".
        mockMvc.perform(get("/"))
            //executes the print method (using the mock)
            .andDo(print())
            //expects that no error occurred (isOk proves it)
            .andExpect(status().isOk)
            //and also expects that the attribute message is equal to the one 
            //previously initialized (the app.message value)
            .andExpect(model().attribute("message", equalTo(message)))
    }
}
