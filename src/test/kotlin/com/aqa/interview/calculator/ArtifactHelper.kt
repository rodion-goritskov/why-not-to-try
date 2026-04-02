package com.aqa.interview.calculator

import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/**
 * Helpers for publishing test artifacts (files, reports, screenshots, etc.)
 * so they appear in the CI service run results.
 *
 * Usage:
 * ```
 * ArtifactHelper.publish("my-report.txt", File("/tmp/generated-report.txt"))
 * ```
 */
object ArtifactHelper {

    private val sharedVolumeDir = File("/shared-volume")

    /**
     * Copies [source] into the shared artifact volume and emits a service
     * message so the CI service collects it automatically.
     *
     * @param name  Display name shown in the CI results (e.g. "Coverage Report").
     * @param source  The file to publish. Can be anywhere on the filesystem.
     * @return the destination file inside the shared volume.
     */
    fun publish(name: String, source: File): File {
        require(source.isFile) { "Source file does not exist or is a directory: $source" }
        val destination = sharedVolumeDir.resolve(source.name)
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING)
        println("##aqa[artifact name='${escapeName(name)}' path='${destination.name}']")
        return destination
    }

    /**
     * Copies [source] into the shared artifact volume under [targetPath] and
     * emits a service message so the CI service collects it.
     *
     * Use this overload when you want to control the destination filename
     * or place the artifact in a subdirectory.
     *
     * @param name  Display name shown in the CI results.
     * @param source  The file to publish.
     * @param targetPath  Relative path inside the shared volume (e.g. "reports/output.html").
     * @return the destination file inside the shared volume.
     */
    fun publish(name: String, source: File, targetPath: String): File {
        require(source.isFile) { "Source file does not exist or is a directory: $source" }
        val destination = sharedVolumeDir.resolve(targetPath)
        destination.parentFile?.mkdirs()
        Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING)
        println("##aqa[artifact name='${escapeName(name)}' path='$targetPath']")
        return destination
    }

    private fun escapeName(name: String): String = name.replace("'", "")
}
