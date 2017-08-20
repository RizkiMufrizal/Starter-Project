package org.rizki.mufrizal.starter.backend.repository

import org.rizki.mufrizal.starter.backend.domain.Barang
import org.springframework.data.repository.PagingAndSortingRepository


/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 5:53 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.repository
 * @File BarangRepository
 *
 */
interface BarangRepository : PagingAndSortingRepository<Barang, String>