<script setup>
import { QuillEditor } from '@vueup/vue-quill';
import '@vueup/vue-quill/dist/vue-quill.snow.css';
</script>

<template>
    <div class="container border p-3">
        <p>댓글 입력</p>
        <hr>
        <QuillEditor theme="snow" contentType="html" toolbar="mini" />
        <div class="container mt-3 text-end p-0">
                <button class="btn btn-success btn-sm" @click="moveToModify">댓글 작성</button>
        </div>
    </div>

</template>

<style scoped>

</style>