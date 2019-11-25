package com.vitovalov.kino

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.vitovalov.kino.domain.model.ShowBo
import com.vitovalov.kino.domain.usecase.GetShowList
import com.vitovalov.kino.ui.mapper.ShowListUoMapper
import com.vitovalov.kino.ui.model.ShowUo
import com.vitovalov.kino.ui.showlist_screen.ShowListContract
import com.vitovalov.kino.ui.showlist_screen.ShowListPresenter
import org.junit.Before
import org.junit.Test
import java.net.UnknownHostException

class ShowListPresenterTest {

    private val view: ShowListContract.View = mock()
    private val useCase: GetShowList = mock()
    private val mapper: ShowListUoMapper = mock()

    private val presenter by lazy {
        ShowListPresenter(
            useCase,
            mapper
        )
    }

    @Before
    fun setup() {
        presenter.onViewReady(view)
    }

    @Test
    fun `when view is ready, show progress is called`() {
        verify(view).showProgress()
    }

    @Test
    fun `use case is executed when view is ready`() {
        verify(useCase).invoke(any(), any(), any())
    }

    @Test
    fun `when user scrolls to bottom, new page is loaded`() {
        presenter.onPageEnd()

        verify(useCase).invoke(any(), any(), any())
    }

    @Test
    fun `on generic error, view shows error`() {
        presenter.onGenericError(Error())

        verify(view).showError()
    }

    @Test
    fun `after presenter success load, same results are received by view`() {
        given { mapper.toUo(givenShowList()) }.willReturn(givenShowListUo())

        presenter.handleSuccess(givenShowList())

        verify(mapper).toUo(givenShowList())

        verify(view).showList(givenShowListUo())
    }

    @Test
    fun `after presenter error load, view shows error`() {
        presenter.handleError(com.vitovalov.kino.domain.Failure.Error(IllegalArgumentException()))

        verify(view).showError()
    }

    @Test
    fun `after presenter error load, if offline, view shows error offline`() {
        presenter.handleError(com.vitovalov.kino.domain.Failure.Error(UnknownHostException()))

        verify(view).showOfflineError()
    }

    private fun givenShowList(): List<ShowBo> {
        return listOf(ShowBo("", 3, "", 2.0))
    }

    private fun givenShowListUo(): List<ShowUo> {
        return listOf(ShowUo("", 3, "", 2.0))
    }
}
