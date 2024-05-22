<script>
export default {
    setup() {
        const hideFooter = window.location.pathname === '/map';
        return { hideFooter };
    }
};
</script>

<template>
    <div v-if="!hideFooter" class="mt-auto py-3 bg-light nav-custom z-n1">
        <div class="container text-center">
            <span class="mb-3 mb-md-0 text-muted">&copy; 2023 MJBIN, Inc</span>
        </div>
    </div>
</template>

<style scoped>
.nav-custom {
    z-index: 5;
}
</style>