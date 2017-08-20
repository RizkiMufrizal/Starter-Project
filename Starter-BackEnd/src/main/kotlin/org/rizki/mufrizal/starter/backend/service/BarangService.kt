package org.rizki.mufrizal.starter.backend.service

import org.rizki.mufrizal.starter.backend.domain.Barang
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional


/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 7:03 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.service
 * @File BarangService
 *
 */
interface BarangService {
    fun getBarangs(pageable: Pageable): Page<Barang>
    fun saveBarang(barang: Barang): Barang
    fun updateBarang(barang: Barang): Barang
    fun deleteBarang(idBarang: String)
    fun getBarang(idBarang: String): Optional<Barang>
}