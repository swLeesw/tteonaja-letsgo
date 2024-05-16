<script setup>
import { ref } from 'vue';
import { RouterLink } from 'vue-router';
defineProps({ elem: Object });


</script>

<template>
    <tr>
        <th scope="row">{{ elem.index + 1 }}</th>
        <td>
            <RouterLink :to="{ name: 'free-detail', params: { id: elem.article.articleNo } }">{{
                elem.article.subject }}</RouterLink>
        </td>
        <td>{{ elem.article.userId }}</td>
        <td>{{ elem.article.registerTime }}</td>
        <td>{{ elem.article.hit }}</td>
    </tr>
</template>

<style scoped></style>