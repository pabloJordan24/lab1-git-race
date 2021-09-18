package es.unizar.webeng.hello.controller

//import libraries (junit used for testing, spring for web app).
import org.hamcrest.CoreMatchers.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

//Annotation used to apply only configuration relevant to MVC test.
@WebMvcTest(HelloController::class)

class HelloControllerMVCTests {
    //we use this annotation to inicialize the properties
    //we take advantage of Spring´s expression language to access the values
    //using ${value}.
    @Value("\${app.message}")

    //a variable is initalized to be able to store the values of the test.
    //we want to prove
    private lateinit var message: String

    //Spring can enable dependency injection in an automatic mode. This can be 
    //done thanks to this AutoWired annotation. (Great option if you want to mantain the
    //code because it updates automatically).
    @Autowired
    private lateinit var mockMvc: MockMvc

    //Annotation used to specify the body of the unit test.
    @Test
    fun testMessage() {
        //method .perfom executes a get operation to the URL specified. In this 
        //case, to the root dir.
        mockMvc.perform(get("/"))
            //executes the print method
            .andDo(print())
            //expects that no error occurred (isOk proves it)
            .andExpect(status().isOk)
            //and also expects that the attribute message is equal to the one 
            //previously initialized (the app.message value)
            .andExpect(model().attribute("message", equalTo(message)))
    }
}

