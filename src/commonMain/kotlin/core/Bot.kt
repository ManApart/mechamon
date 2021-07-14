package core

import data.arms
import data.cores
import data.heads

class Bot(
    var head: Head = heads["Standard"]!!,
    var core: Core = cores["Standard"]!!,
    var armRight: Arm = arms["StandardRight"]!!,
    var armLeft: Arm = arms["StandardLeft"]!!
) {
    var mp = 0

}