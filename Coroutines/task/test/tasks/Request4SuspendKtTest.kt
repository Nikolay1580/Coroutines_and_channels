package tasks

import contributors.MockGithubService
import contributors.expectedConcurrentResults
import contributors.expectedResults
import contributors.testRequestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class Request4SuspendKtTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testSuspend() = runTest {
        val startTime = currentTime
        val result = loadContributorsSuspend(MockGithubService, testRequestData)
        Assert.assertEquals("wrong result for loadContributorsSuspend", expectedResults.users, result)
        val totalTime = currentTime - startTime

        Assert.assertEquals(
            "v-time should be 4000ms",
            expectedResults.timeFromStart, totalTime
        )


    }
}