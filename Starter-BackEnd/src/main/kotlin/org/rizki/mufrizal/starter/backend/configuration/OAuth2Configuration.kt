package org.rizki.mufrizal.starter.backend.configuration

import org.apache.commons.io.IOUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory
import org.springframework.security.web.access.channel.ChannelProcessingFilter
import java.io.File
import java.nio.charset.Charset
import javax.sql.DataSource

/**
 *
 * @Author Rizki Mufrizal <mufrizalrizki@gmail.com>
 * @Web <https://RizkiMufrizal.github.io>
 * @Since 20 August 2017
 * @Time 6:18 PM
 * @Project Starter-BackEnd
 * @Package org.rizki.mufrizal.starter.backend.configuration
 * @File OAuth2Configuration
 *
 */
@Configuration
open class OAuth2Configuration {

    @Configuration
    @EnableAuthorizationServer
    protected class AuthorizationServerConfiguration : AuthorizationServerConfigurerAdapter() {

        @Autowired
        @Qualifier("authenticationManagerBean")
        private lateinit var authenticationManager: AuthenticationManager

        @Autowired
        private lateinit var dataSource: DataSource

        @Bean
        fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
            val converter = JwtAccessTokenConverter()
            val keyStoreKeyFactory = KeyStoreKeyFactory(ClassPathResource("keys${File.separator}jwt.jks"), "mufrizalrizki".toCharArray())
            converter.setKeyPair(keyStoreKeyFactory.getKeyPair("jwt"))
            return converter
        }

        @Bean
        fun tokenStore(): TokenStore {
            return JdbcTokenStore(dataSource)
        }

        @Throws(Exception::class)
        override fun configure(authorizationServerEndpointsConfigurer: AuthorizationServerEndpointsConfigurer?) {
            authorizationServerEndpointsConfigurer
                    ?.accessTokenConverter(jwtAccessTokenConverter())
                    ?.tokenStore(tokenStore())
                    ?.authenticationManager(authenticationManager)
        }

        @Throws(Exception::class)
        override fun configure(clientDetailsServiceConfigurer: ClientDetailsServiceConfigurer?) {
            clientDetailsServiceConfigurer
                    ?.jdbc(dataSource)
        }

    }

    @Configuration
    @EnableResourceServer
    protected class ResourceServerConfiguration : ResourceServerConfigurerAdapter() {

        private val RESOURCE_ID = "RESOURCE_ID_BARANG"

        @Bean
        @Qualifier("jwtAccessTokenConverter")
        fun jwtAccessTokenConverter(): JwtAccessTokenConverter {
            val converter = JwtAccessTokenConverter()
            val resource = ClassPathResource("keys${File.separator}public.txt")
            val publicKey: String? = IOUtils.toString(resource.inputStream, Charset.defaultCharset())
            converter.setVerifierKey(publicKey)
            return converter
        }

        override fun configure(resourceServerSecurityConfigurer: ResourceServerSecurityConfigurer?) {
            resourceServerSecurityConfigurer
                    ?.resourceId(RESOURCE_ID)
        }

        override fun configure(httpSecurity: HttpSecurity?) {
            httpSecurity
                    ?.authorizeRequests()
                    ?.antMatchers("/**")
                    ?.fullyAuthenticated()
                    ?.and()
                    ?.addFilterBefore(CorsConfiguration(), ChannelProcessingFilter::class.java)
        }

    }

}