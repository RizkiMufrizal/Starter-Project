package org.rizki.mufrizal.starter.backend.domain

import org.hibernate.annotations.GenericGenerator
import org.hibernate.validator.constraints.NotEmpty
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 5:46 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.domain
 * @File Barang
 *
 */
@Entity
@Table(name = "tb_barang")
data class Barang(
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "uuid2")
        @Column(length = 36, name = "id_barang")
        val idBarang: String? = null,
        @NotEmpty
        @NotNull
        @Column(length = 50, name = "nama_barang")
        val namaBarang: String? = null,
        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(length = 36, name = "jenis_barang")
        val jenisBarang: JenisBarang? = null
)