package com.cvitirinyu.codingchallenge.starwars.data.network

import com.cvitirinyu.codingchallenge.starwars.data.network.NetworkCallHandlerImpl
import com.cvitirinyu.codingchallenge.starwars.data.network.ServiceResult
import com.cvitirinyu.codingchallenge.starwars.data.network.model.StarWarsMissionResponse
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.*
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class NetworkCallHandlerTest {
    private val message = "MESSAGE"
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private lateinit var sut: NetworkCallHandlerImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this, true)
        Dispatchers.setMain(testCoroutineDispatcher)
        sut = NetworkCallHandlerImpl()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
        clearAllMocks()
    }

    @Test
    fun `makeNetworkCall returns service success when call is suuccessful and response has body`() {
        runBlocking {
            val mockResponse = mockk<Response<List<StarWarsMissionResponse>>>()
            val missionsFromJsonFeed =
                listOf(
                    StarWarsMissionResponse(
                        id = 1,
                        description = "Rebel Forces spotted on Hoth. Quell their rebellion for the Empire.",
                        title = "Stop Rebel Forces",
                        timestamp = "2015-06-18T17:02:02.614Z",
                        image = "https://raw.githubusercontent.com/phunware-services/dev-interview-homework/master/Images/Battle_of_Hoth.jpg",
                        date = "2015-06-18T23:30:00.000Z",
                        locationLineOne = "Hoth",
                        locationLineTwo = "Anoat System"
                    )
                )
            every { mockResponse.code() } returns 200
            every { mockResponse.message() } returns message
            every { mockResponse.isSuccessful } returns true
            every { mockResponse.body() } returns missionsFromJsonFeed

            suspend fun testCall(): Response<List<StarWarsMissionResponse>> =
                withContext(Dispatchers.Main) {
                    mockResponse
                }

            val response = sut.makeNetworkCall(call = ::testCall)

            response.assertType<ServiceResult.Success<List<StarWarsMissionResponse>>> {
                data.first().apply {
                    id.assertEqual(1)
                    description.assertEqual("Rebel Forces spotted on Hoth. Quell their rebellion for the Empire.")
                    title.assertEqual("Stop Rebel Forces")
                    timestamp.assertEqual("2015-06-18T17:02:02.614Z")
                    image.assertEqual("https://raw.githubusercontent.com/phunware-services/dev-interview-homework/master/Images/Battle_of_Hoth.jpg")
                    date.assertEqual("2015-06-18T23:30:00.000Z")
                    locationLineOne.assertEqual("Hoth")
                    locationLineTwo.assertEqual("Anoat System")
                }
            }
        }
    }
}

inline fun <reified T> Any?.assertType(action: T.() -> Unit) {
    (this is T).assertTrue()
    action(this as T)
}

fun Boolean?.assertTrue() {
    assertNotNull()
    this?.let { Assert.assertTrue(it) }
}

fun <T> T.assertNotNull() {
    Assert.assertNotNull(this)
}

fun <T> T.assertEqual(expected: T) {
    Assert.assertEquals(expected, this)
}