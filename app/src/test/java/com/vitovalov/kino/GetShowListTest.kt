package com.vitovalov.kino

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vitovalov.kino.domain.ShowListRepository
import com.vitovalov.kino.domain.model.ShowBo
import com.vitovalov.kino.domain.usecase.GetShowList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers

class GetShowListTest {

    private lateinit var getShowList: GetShowList

    private val repository: ShowListRepository = mock()

    @Before
    fun setup() {
        getShowList = GetShowList(repository)
    }

    @Test
    fun `when getShowList is executed, repository getShowList is called`() {

        fun handleSuccess(items: List<ShowBo>) {
            Assert.assertEquals(givenFakeList(), items)
        }

        runBlocking {
            whenever(repository.getShowList(ArgumentMatchers.anyInt())).thenReturn(givenFakeList())
            getShowList.invoke(CoroutineScope(Job()), GetShowList.Params(2)) {
                it.either(any(), ::handleSuccess)
            }
            verify(repository).getShowList(any())
        }
    }

    private fun givenFakeList() = listOf(ShowBo("test", 0, "test", 0.0))
}