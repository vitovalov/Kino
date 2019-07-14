package com.vitovalov.kino.data.local

import com.vitovalov.kino.data.local.mapper.ShowLoMapper
import com.vitovalov.kino.data.local.model.ShowLo
import com.vitovalov.kino.domain.model.ShowBo
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShowListRealmDataSource(
    private val showLoMapper: ShowLoMapper
) : ShowListLocalDataSource {

    override suspend fun getShowList(page: Int): List<ShowBo> {
        val realm: Realm = Realm.getDefaultInstance()
        val items = realm.where(ShowLo::class.java).equalTo("page", page).findAll().toList()
        return showLoMapper.toBo(items)
    }

    override suspend fun saveShowList(showList: List<ShowBo>, page: Int) =
        withContext(Dispatchers.IO) {
            val items = showLoMapper.toLo(showList, page)
            val realm: Realm = Realm.getDefaultInstance()
            realm.executeTransaction {
                items.forEach {
                    realm.copyToRealmOrUpdate(it)
                }
            }
            realm.close()
        }
}
