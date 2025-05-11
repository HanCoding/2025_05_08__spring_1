package com.back.domain.member.member.entity

import com.back.global.jpa.entity.BaseTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    @Column(nullable = false, unique = true)
    val username: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    var nickname: String,
) : BaseTime(){
    override fun toString(): String {
        return "Member(username='$username', password='$password', name='$nickname')"
    }
}