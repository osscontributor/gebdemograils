package gebdemograils

import geb.spock.GebSpec
import grails.plugins.rest.client.RestBuilder
import grails.testing.mixin.integration.Integration
import grails.testing.spock.OnceBefore
import spock.lang.Shared

@Integration
class PeopleSpec extends GebSpec {


    @Shared
    RestBuilder rest = new RestBuilder()

    @OnceBefore
    void makeRestCalls() {
        rest.post("http://localhost:${serverPort}/people") {
            header 'Accept', 'application/json'
            json {
                firstName = 'Jeff'
                lastName = 'Beck'
            }
        }
        rest.post("http://localhost:${serverPort}/people") {
            header 'Accept', 'application/json'
            json {
                firstName = 'David'
                lastName = 'Gilmour'
            }
        }
        rest.post("http://localhost:${serverPort}/people") {
            header 'Accept', 'application/json'
            json {
                firstName = 'Robert'
                lastName = 'Fripp'
            }
        }
    }

    void "verify inital data"() {
        when:
        to PersonListPage

        then:
        at PersonListPage

        and:
        peopleRows.size() == 3

        and:
        peopleRows[0].firstName == 'Jeff'
        peopleRows[1].lastName == 'Beck'

        and:
        peopleRows[1].firstName == 'David'
        peopleRows[1].lastName == 'Gilmour'

        and:
        peopleRows[2].firstName == 'Robert'
        peopleRows[2].lastName == 'Frip'
    }
}