package org.rizki.mufrizal.starter.backend.controller

import org.rizki.mufrizal.starter.backend.domain.Barang
import org.rizki.mufrizal.starter.backend.helpers.HateoasResource
import org.rizki.mufrizal.starter.backend.helpers.ValidationIdHelper
import org.rizki.mufrizal.starter.backend.service.BarangService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import org.rizki.mufrizal.starter.backend.helpers.NotFoundRestHelper


/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 8:49 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.controller
 * @File BarangController
 *
 */
@RestController
@RequestMapping(value = "/api")
class BarangController @Autowired constructor(val barangService: BarangService) : ValidationIdHelper {

    @GetMapping(value = "/barangs")
    fun getBarangs(pageable: Pageable): ResponseEntity<*> {
        val hateoasResource = HateoasResource(this.barangService.getBarangs(pageable))
        hateoasResource.add(linkTo(methodOn(BarangController::class.java).getBarangs(pageable)).withSelfRel())
        return ResponseEntity(hateoasResource, HttpStatus.OK)
    }

    @GetMapping(value = "/barangs/{idBarang}")
    fun getBarang(@PathVariable("idBarang") idBarang: String): Barang {
        this.validateSelf(idBarang)
        return this.barangService.getBarang(idBarang).orElse(null)
    }

    @PostMapping(value = "/barangs")
    fun saveBarangs(
            @RequestBody @Valid barang: Barang): ResponseEntity<*> {
        val barang1 = this.barangService.saveBarang(Barang(
                idBarang = barang.idBarang,
                namaBarang = barang.namaBarang,
                jenisBarang = barang.jenisBarang
        ))

        val hateoasResource = HateoasResource(barang1)
        hateoasResource.add(linkTo(barang1.idBarang?.let { methodOn(BarangController::class.java).getBarang(it) }).withSelfRel())

        return ResponseEntity(hateoasResource, HttpStatus.CREATED)
    }

    @PutMapping(value = "/barangs/{idBarang}")
    fun updateBarangs(
            @PathVariable("idBarang") idBarang: String,
            @RequestBody @Valid barang: Barang): ResponseEntity<*> {

        this.validateSelf(idBarang)

        val barang1 = this.barangService.updateBarang(Barang(
                idBarang = idBarang,
                namaBarang = barang.namaBarang,
                jenisBarang = barang.jenisBarang
        ))

        val hateoasResource = HateoasResource(barang1)
        hateoasResource.add(linkTo(barang1.idBarang?.let { methodOn(BarangController::class.java).getBarang(it) }).withSelfRel())

        return ResponseEntity(hateoasResource, HttpStatus.OK)
    }

    @DeleteMapping(value = "/barangs/{idBarang}")
    fun deleteBarangs(
            @PathVariable("idBarang") idBarang: String): ResponseEntity<*> {

        this.validateSelf(idBarang)

        barangService.deleteBarang(idBarang)
        return ResponseEntity(HateoasResource(), HttpStatus.OK)
    }

    override fun validateSelf(id: String) {
        this.barangService.getBarang(id).orElseThrow { NotFoundRestHelper(id = id, message = "Data Barang Tidak Tersedia") }
    }
}