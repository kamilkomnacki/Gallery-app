package komnacki.gallery.login.rxutils

import io.reactivex.Scheduler

abstract class BaseSchedulerProvider(
    val mainThread: Scheduler,
    val io: Scheduler,
    val computation: Scheduler
)