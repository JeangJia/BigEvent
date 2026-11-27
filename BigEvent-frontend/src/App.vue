<script setup lang="ts">
import { onMounted, ref } from 'vue'

import { getArticleList, type Article } from '@/api/article'

const list = ref<Article[]>([])

onMounted(async () => {
    try {
        const { records } = await getArticleList({ pageNum: 1, pageSize: 10 })
        list.value = records
        console.log(records)
    } catch (err) {
        console.error(err)
    }
})
</script>

<template>
    <div class="page">
        <div class="table-wrap">
            <table class="data-table">
                <thead>
                    <tr>
                        <th scope="col">id</th>
                        <th scope="col">title</th>
                        <th scope="col">content</th>
                        <th scope="col">state</th>
                        <th scope="col">create_time</th>
                        <th scope="col">update_time</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="item in list" :key="item.id">
                        <td class="cell-id">{{ item.id }}</td>
                        <td class="cell-title">{{ item.title }}</td>
                        <td class="cell-content" :title="item.content">{{ item.content }}</td>
                        <td>
                            <span
                                class="badge"
                                :class="
                                    item.state === '已发布' ? 'badge--published' : 'badge--draft'
                                "
                            >
                                {{ item.state }}
                            </span>
                        </td>
                        <td class="cell-time">{{ item.createTime }}</td>
                        <td class="cell-time">{{ item.updateTime }}</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<style scoped>
.page {
    min-height: 100vh;
    padding: 40px 24px;
    box-sizing: border-box;
    background: #f5f7fa;
    color: #1f2937;
    font-family:
        system-ui,
        -apple-system,
        'Segoe UI',
        'Microsoft YaHei',
        sans-serif;
}

.table-wrap {
    max-width: 1120px;
    margin: 0 auto;
    overflow-x: auto;
    background: #fff;
    border: 1px solid #e5e7eb;
    border-radius: 12px;
    box-shadow:
        0 1px 2px rgba(16, 24, 40, 0.04),
        0 8px 24px rgba(16, 24, 40, 0.05);
}

.data-table {
    width: 100%;
    min-width: 760px;
    border-collapse: collapse;
    font-size: 14px;
}

.data-table th,
.data-table td {
    padding: 14px 20px;
    text-align: left;
    vertical-align: middle;
}

.data-table th {
    font-size: 12px;
    font-weight: 600;
    letter-spacing: 0.06em;
    text-transform: uppercase;
    white-space: nowrap;
    color: #6b7280;
    background: #f9fafb;
    border-bottom: 1px solid #e5e7eb;
}

.data-table td {
    color: #374151;
    border-bottom: 1px solid #f1f3f5;
}

.data-table tbody tr {
    transition: background-color 0.15s ease;
}

.data-table tbody tr:hover {
    background: #f8fafc;
}

.data-table tbody tr:last-child td {
    border-bottom: none;
}

.cell-id {
    width: 64px;
    color: #9ca3af;
    font-variant-numeric: tabular-nums;
}

.cell-title {
    font-weight: 600;
    color: #111827;
}

.cell-content {
    max-width: 360px;
    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
    color: #6b7280;
}

.cell-time {
    color: #6b7280;
    font-size: 13px;
    font-variant-numeric: tabular-nums;
    white-space: nowrap;
}

.badge {
    display: inline-block;
    padding: 2px 10px;
    font-size: 12px;
    font-weight: 500;
    line-height: 20px;
    white-space: nowrap;
    border: 1px solid transparent;
    border-radius: 999px;
}

.badge--published {
    color: #047857;
    background: #ecfdf5;
    border-color: #a7f3d0;
}

.badge--draft {
    color: #b45309;
    background: #fffbeb;
    border-color: #fde68a;
}

@media (max-width: 640px) {
    .page {
        padding: 20px 12px;
    }

    .data-table th,
    .data-table td {
        padding: 12px 14px;
    }
}
</style>
