package org.rizki.mufrizal.starter.backend.helpers

import org.springframework.hateoas.VndErrors
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus


/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 7:04 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.helpers
 * @File ErrorHandlerAdviceHelper
 *
 */
@ControllerAdvice
class ErrorHandlerAdviceHelper {

    @ResponseBody
    @ExceptionHandler(NotFoundRestHelper::class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    fun idNotFoundExceptionHandler(notFoundRestHelper: NotFoundRestHelper): VndErrors {
        return VndErrors("error", notFoundRestHelper.message)
    }

}