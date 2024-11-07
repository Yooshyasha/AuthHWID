package com.yooshyasha.authhwid.security

import com.yooshyasha.authhwid.service.JwtService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorizationHeader.substring(7)

        if (jwtService.isTokenValid(token)) {
            val authentication = UsernamePasswordAuthenticationToken(null, null, emptyList())

            SecurityContextHolder.getContext().authentication = authentication
        } else {
            response.status = HttpStatus.UNAUTHORIZED.value()
            response.writer.write("Invalid or expired JWT token")
            return
        }

        filterChain.doFilter(request, response)
    }
}