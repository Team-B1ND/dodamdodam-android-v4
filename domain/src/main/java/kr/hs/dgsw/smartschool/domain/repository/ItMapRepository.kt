package kr.hs.dgsw.smartschool.domain.repository

import kr.hs.dgsw.smartschool.domain.model.itmap.Company

interface ItMapRepository {

    suspend fun getAllCompanies(): List<Company>

    suspend fun getCompanyById(id: Int): Company

}