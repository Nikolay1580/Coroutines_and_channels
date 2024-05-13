package tasks

import contributors.MockGithubService
import contributors.progressResults
import contributors.testRequestData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import tasks.loadContributorsProgress

class Request6ProgressKtTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testProgress() = runTest {
        val startTime = currentTime
        var i = 0
        loadContributorsProgress(MockGithubService, testRequestData) {
                users, _ ->
            val expected = progressResults[i++]
            val time = currentTime - startTime

            Assert.assertEquals("expected intermediate result after: ${expected.timeFromStart} virtual ms:",
                expected.timeFromStart, time)

            Assert.assertEquals("wrong intermediate result: $time:", expected.users, users)
        }
    }
}