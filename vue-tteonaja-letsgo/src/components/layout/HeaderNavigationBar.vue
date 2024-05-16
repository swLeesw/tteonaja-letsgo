<script setup>
import { RouterLink, RouterView } from 'vue-router';
import { ref } from 'vue';

</script>

<template>

    <nav class="navbar navbar-expand-md bg-body-tertiary sticky-top">
        <div class="container-fluid">
            <RouterLink :to="{ name: 'home' }" class="navbar-brand">
                <img src="@/assets/logo.png" class="ratio" alt="..." style="width: 25%" />
            </RouterLink>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll"
                aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarScroll">
                <ul class="navbar-nav ms-auto my-2 my-lg-0 navbar-nav-scroll p-1" style="--bs-scroll-height: 100px">
                    <li class="nav-item me-3">
                        <a class="nav-link btn-hover-effect rounded-pill" href="#">홈</a>
                    </li>
                    <li class="nav-item me-3">
                        <a class="nav-link btn-hover-effect rounded-pill" href="#">여행</a>
                        <!-- <RouterLink :to="{ name: 'trip' }" class="nav-link align-middle btn-hover-effect rounded-pill">여행</RouterLink> -->
                    </li>
                    <li class="nav-item me-3 dropdown">
                        <a class="nav-link dropdown-toggle btn-hover-effect rounded-pill" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false" href="#">게시판</a>
                        <!-- <RouterLink :to="{ name: 'free' }" class="nav-link align-middle btn-hover-effect rounded-pill">자유게시판</RouterLink> -->
                        <ul class="dropdown-menu">
                            <li class="dropdown-item">여행 계획</li>
                            <li class="dropdown-item">
                                <RouterLink :to="{ name: 'trip-list' }">관광지 후기
                                </RouterLink>
                            </li>
                            <li class="dropdown-item">
                                <RouterLink :to="{ name: 'free-list' }">자유 게시판
                                </RouterLink>
                            </li>
                        </ul>
                    </li>
                    <li class="nav-item me-3">
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
    <!-- <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Carousel</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse"
                aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link disabled">Disabled</a>
                    </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                    <button class="btn btn-outline-success" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav> -->
</template>

<style scoped>
.btn-hover-effect:hover {
    background-color: lightskyblue;
    color: white;
    border-color: #004085;
    transition: background-color 0.3s ease, color 0.3s ease, border-color 0.3s ease;
}
</style>