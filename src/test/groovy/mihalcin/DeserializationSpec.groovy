package mihalcin

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import spock.lang.Specification

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS

class DeserializationSpec extends Specification {

    private ObjectMapper mapper = new ObjectMapper()

    def setup() {
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS)
        mapper.registerModule(new JavaTimeModule())
    }

    // BEFORE RUNNING FOLLOWING TEST, PUT BREAKPOINT IN CONSTRUCTOR OF Brick CLASS

    def "deserialize House"() {
        when: "I use flat, brick is just a string"
        /*
        {
            "bricks": ["aa", "abcd"],
            "houseNumber": 99
        }
         */
        def house = mapper.readValue("{ \"bricks\": [\"aa\", \"abcd\"], \"houseNumber\": 99 }", House.class)

        then: "Brick constructor is invoked"
        with(house) {
            bricks == [new Brick("aa"), new Brick("abcd")]
            houseNumber == 99
        }

        when: "I use nested, brick is an object with field code"
        /*
        {
            "bricks": [{
                    "code": "aa"
                }, {
                    "code": "abcd"
                }
            ],
            "houseNumber": 99
        }
         */
        house = mapper.readValue("{ \"bricks\": [{ \"code\": \"aa\" }, { \"code\": \"abcd\" } ], \"houseNumber\": 99 }", House.class)

        then: "Brick constructor is not invoked, instead reflection is used to set values"
        with(house) {
            bricks == [new Brick("aa"), new Brick("abcd")]
            houseNumber == 99
        }
    }

}
