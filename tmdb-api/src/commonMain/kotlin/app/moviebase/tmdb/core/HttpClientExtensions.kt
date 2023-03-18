package app.moviebase.tmdb.core

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.util.pipeline.PipelinePhase


internal suspend inline fun <reified T> HttpClient.getByPaths(
    vararg paths: String,
    block: HttpRequestBuilder.() -> Unit = {}
): T = get(urlString = buildPaths(*paths), block = block).body()

internal suspend inline fun <reified T> HttpClient.postByPaths(
    vararg paths: String,
    block: HttpRequestBuilder.() -> Unit = {},
): T = post(urlString = buildPaths(*paths), block = block).body()

internal suspend inline fun <reified T> HttpClient.deleteByPaths(
    vararg paths: String,
    block: HttpRequestBuilder.() -> Unit = {},
): T = delete(urlString = buildPaths(*paths), block = block).body()

private fun buildPaths(vararg paths: String): String = paths.joinToString(separator = "/")

typealias RequestInterceptor = suspend (HttpRequestBuilder) -> Unit
typealias ResponseInterceptor = suspend (HttpClientCall) -> Unit

internal fun HttpClient.interceptRequest(phase: PipelinePhase = HttpRequestPipeline.Render, interceptor: RequestInterceptor) =
    requestPipeline.intercept(phase) { interceptor(context) }
