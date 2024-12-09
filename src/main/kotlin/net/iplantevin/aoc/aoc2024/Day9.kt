package net.iplantevin.aoc.aoc2024

object Day9 {

    fun part1(input: String): Long {
        val (fileBlocks, freeSpace) = parseDiskMap(input)
        var blockIndex = fileBlocks.size

        for (freePosition in freeSpace) {
            blockIndex -= 1
            val fileBlock = fileBlocks[blockIndex]
            if (freePosition > fileBlock.position) break
            fileBlock.position = freePosition
        }
        return fileBlockChecksum(fileBlocks)
    }

    fun part2(input: String): Long {
        val (files, freeSpaceRanges) = parseDiskMap2(input)

        for (file in files.reversed()) {
            var freeSpace: RangedFreeSpace? = null
            for (maybeFreeSpace in freeSpaceRanges) {
                if (maybeFreeSpace.blockRange.first >= file.blockRange.first) {
                    // No point in iterating further, as the free space lies beyond the file
                    break
                }
                if (maybeFreeSpace.blockRange.length() >= file.blockRange.length()) {
                    freeSpace = maybeFreeSpace
                    break
                }
            }
            if (freeSpace != null) {
                file.blockRange =
                    LongRange(freeSpace.blockRange.first, freeSpace.blockRange.first + file.blockRange.length() - 1)

                if (file.blockRange.length() == freeSpace.blockRange.length()) {
                    // The range is now "empty"
                    freeSpace.blockRange = LongRange(freeSpace.blockRange.first, freeSpace.blockRange.first - 1)
                } else {
                    val newFreeRangeStart = freeSpace.blockRange.first + file.blockRange.length()
                    freeSpace.blockRange = LongRange(newFreeRangeStart, freeSpace.blockRange.last)
                }
            }
        }
        return rangedFileChecksum(files)
    }

    private fun parseDiskMap(input: String): Pair<List<FileBlock>, List<Int>> {
        val blocks = mutableListOf<FileBlock>()
        val freeSpace = mutableListOf<Int>()
        var fileID = -1
        var diskBlockIndex = 0

        input.forEachIndexed { i, char ->
            val isFile = i % 2 == 0
            if (isFile) fileID++
            val length = char.digitToInt()
            repeat(length) {
                when (isFile) {
                    true -> blocks += FileBlock(fileID, diskBlockIndex)
                    false -> freeSpace += diskBlockIndex
                }
                diskBlockIndex++
            }
        }
        return blocks to freeSpace
    }

    private fun parseDiskMap2(input: String): Pair<List<RangedFile>, MutableList<RangedFreeSpace>> {
        val files = mutableListOf<RangedFile>()
        val freeBlockRanges = mutableListOf<RangedFreeSpace>()
        var fileID = -1
        var diskBlockIndex = 0L

        input.forEachIndexed { i, char ->
            val length = char.digitToInt()
            when (i % 2 == 0) {
                true -> {
                    fileID++
                    files += RangedFile(fileID, LongRange(diskBlockIndex, diskBlockIndex + length - 1))
                }

                false -> freeBlockRanges += RangedFreeSpace(LongRange(diskBlockIndex, diskBlockIndex + length - 1))
            }
            diskBlockIndex += length
        }
        return files to freeBlockRanges
    }

    private fun fileBlockChecksum(fileBlocks: List<FileBlock>): Long =
        fileBlocks.sumOf { block -> block.fileId * block.position.toLong() }

    private fun rangedFileChecksum(files: List<RangedFile>): Long = files.sumOf { file ->
        file.blockRange.sumOf { blockPosition -> blockPosition * file.id }
    }

    private fun LongRange.length() = endInclusive - start + 1

    private data class FileBlock(val fileId: Int, var position: Int)

    private data class RangedFile(val id: Int, var blockRange: LongRange)

    private data class RangedFreeSpace(var blockRange: LongRange)
}
