package org.rizki.mufrizal.starter.backend.helpers

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 7:05 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.helpers
 * @File NotFoundRestHelper
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundRestHelper(id: String, message: String) : RuntimeException("$message $id")