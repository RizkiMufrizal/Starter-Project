package org.rizki.mufrizal.starter.backend.service.impl

import org.rizki.mufrizal.starter.backend.domain.Barang
import org.rizki.mufrizal.starter.backend.repository.BarangRepository
import org.rizki.mufrizal.starter.backend.service.BarangService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 8:44 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.service.impl
 * @File BarangServiceImpl
 *
 */
@Service
@Transactional(readOnly = true)
class BarangServiceImpl @Autowired constructor(val barangRepository: BarangRepository) : BarangService {

    override fun getBarangs(pageable: Pageable): Page<Barang> {
        return barangRepository.findAll(pageable)
    }

    @Transactional
    override fun saveBarang(barang: Barang): Barang {
        return barangRepository.save(barang)
    }

    @Transactional
    override fun updateBarang(barang: Barang): Barang {
        return barangRepository.save(barang)
    }

    @Transactional
    override fun deleteBarang(idBarang: String) {
        barangRepository.delete(idBarang)
    }

    override fun getBarang(idBarang: String): Optional<Barang> {
        return barangRepository.findByIdBarang(idBarang)
    }
}