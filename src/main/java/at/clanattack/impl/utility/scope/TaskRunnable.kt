package at.clanattack.impl.utility.scope

import at.clanattack.utility.scope.ITask

class TaskRunnable(private val instance: ITask, private val run: ITask.() -> Unit) : Runnable {

    override fun run() {
        instance.run()
    }


}