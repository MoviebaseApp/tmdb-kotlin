package app.moviebase.tmdb.api

import app.moviebase.tmdb.model.*
import app.moviebase.tmdb.remote.endPointV4
import app.moviebase.tmdb.remote.json
import app.moviebase.tmdb.remote.parameterLanguage
import app.moviebase.tmdb.remote.parameterPage
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class Tmdb4ListApi internal constructor(private val client: HttpClient) {

    /**
     * This method will retrieve a list by id.
     * Private lists can only be accessed by their owners and therefore require a valid user access token.
     */
    suspend fun getList(
        listId: Int,
        page: Int,
        language: String? = null,
        sortBy: TmdbListSortBy? = null,
        sortOrder: TmdbSortOrder = TmdbSortOrder.DESC
    ): Tmdb4List = client.get {
        endPointList(listId)
        parameterPage(page)
        parameterLanguage(language)
        sortBy?.let { parameterSortBy(it, sortOrder) }
    }.body()

    /**
     * This method will create a new list.
     * You will need to have valid user access token in order to create a new list.
     */
    suspend fun create(request: Tmdb4CreateListRequest): TmdbStatusResult = client.post {
        endPointV4("list")
        json()
        setBody(request)
    }.body()

    /**
     * This method will let you update the details of a list.
     * You must be the owner of the list and therefore have a valid user access token in order to edit it.
     */
    suspend fun update(listId: Int, request: Tmdb4UpdateListRequest): TmdbStatusResult = client.put {
        endPointList(listId)
        json()
        setBody(request)
    }.body()

    /**
     * This method lets you clear all of the items from a list in a single request. This action cannot be reversed so use it with caution.
     * You must be the owner of the list and therefore have a valid user access token in order to clear a list.
     */
    suspend fun clear(listId: Int): TmdbStatusResult = client.get {
        endPointList(listId, "clear")
    }.body()

    /**
     * This method will delete a list by id. This action is not reversible so take care when issuing it.
     * You must be the owner of the list and therefore have a valid user access token in order to delete it.
     */
    suspend fun delete(listId: Int): TmdbStatusResult = client.delete {
        endPointList(listId)
    }.body()

    /**
     * This method will let you add items to a list. We support essentially an unlimited number of items to be posted at a time. Both movie and TV series are support.
     * The results of this query will return a results array. Each result includes a success field. If a result is false this will usually indicate that the item already exists on the list. It may also indicate that the item could not be found.
     * You must be the owner of the list and therefore have a valid user access token in order to add items to a list.
     */
    suspend fun addItems(listId: Int, items: Tmdb4ItemsRequest): TmdbStatusResult = client.post {
        endPointList(listId, "items")

        json()
        setBody(items)
    }.body()

    /**
     * This method will let you update an individual item on a list. Currently, only adding a comment is suported.
     * You must be the owner of the list and therefore have a valid user access token in order to edit items.
     */
    suspend fun updateItems(listId: Int, items: Tmdb4UpdateItemsRequest): TmdbStatusResult = client.put {
        endPointList(listId, "items")

        json()
        setBody(items)
    }.body()

    /**
     * This method will let you remove items from a list. You can remove multiple items at a time.
     * You must be the owner of the list and therefore have a valid user access token in order to delete items from it.
     */
    suspend fun removeItems(listId: Int, items: Tmdb4ItemsRequest): TmdbStatusResult = client.delete {
        endPointList(listId, "items")

        json()
        setBody(items)
    }.body()

    /**
     * This method lets you quickly check if the item is already added to the list.
     * You must be the owner of the list and therefore have a valid user access token in order to check an item status.
     */
    suspend fun checkItemStatus(listId: Int, mediaId: Int, mediaType: TmdbMediaType): Tmdb4ItemStatus = client.get {
        endPointList(listId, "item_status")
        parameter("media_id", mediaId.toString())
        parameter("media_type", mediaType.value)
    }.body()

    private fun HttpRequestBuilder.endPointList(listId: Int, vararg paths: String) {
        endPointV4("list", listId.toString(), *paths)
    }

    private fun HttpRequestBuilder.parameterSortBy(sortBy: TmdbListSortBy, sortOrder: TmdbSortOrder) {
        parameter("sort_by", sortBy.value + sortOrder.value)
    }
}
