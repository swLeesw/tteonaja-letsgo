<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
    <nav class="navbar navbar-expand-md bg-body-tertiary sticky-top">
        <div class="container-fluid">
            <router-link :to="{ name: 'home' }" class="navbar-brand">
                <img src="@/assets/logo.png" class="ratio" alt="..." style="width: 25%" />
            </router-link>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll p-1" style="--bs-scroll-height: 100px">
                    <li class="nav-item me-3">
                        <a class="nav-link" href="#">홈</a>
                    </li>
                    <li class="nav-item me-3">
                        <a class="nav-link" href="#">여행</a>
                        <!-- <RouterLink :to="{ name: 'trip' }" class="nav-link align-middle btn-hover-effect rounded-pill">여행</RouterLink> -->
                    </li>
                    <li class="nav-item me-3">
                        <a class="nav-link" href="#">게시판</a>
                        <!-- <RouterLink :to="{ name: 'free' }" class="nav-link align-middle btn-hover-effect rounded-pill">자유게시판</RouterLink> -->
                    </li>
                    <li class="nav-item me-3">
                        <!-- <a class="nav-link" href="#">로그인</a> -->
                        <RouterLink :to="{ name: 'login' }" class="nav-link align-middle btn-hover-effect rounded-pill">
                            로그인</RouterLink>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                            aria-expanded="false">
                            HELP DESK
                        </a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item " href="#">공지사항</a></li>
                            <li><a class="dropdown-item" href="#">FAQ</a></li>
                            <li>
                                <hr class="dropdown-divider" />
                            </li>
                            <li><a class="dropdown-item" href="#">학사규정</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<style scoped>
    .btn-hover-effect:hover {
        background-color: lightskyblue;
        color: white;
        border-color: #004085;
        transition: background-color 0.3s ease, color 0.3s ease, border-color 0.3s ease;
    }
</style>