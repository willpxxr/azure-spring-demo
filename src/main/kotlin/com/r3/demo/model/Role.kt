package com.r3.demo.model

/**
 * NOTE: If an authority is not prefixed with `ROLE_` is won't be found by `hasRole`,
 *       however could be found by `hasAuthority`.
 */
enum class Role {
    ROLE_USER,
    ROLE_ADMIN,
    //NOT_A_TEST_ROLE
}
