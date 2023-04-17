<template>
    <div class="page-wrapper">
        <header class="page-header">
            <p id="server-name">{{ serverName }}</p>
        </header>
        <slot></slot>
        <footer class="page-footer">
            <p id="server-status">{{ serverStatus }}</p>
        </footer>
    </div>
</template>

<script>
export default {
    name: 'HeadAndFoot',
    props: {
        serverName: {
            type: String,
            default: ''
        },
        serverStatus: {
            type: String,
            default: ''
        }
    }
}
</script>

<style scoped>
.page-wrapper {
    display: flex;
    flex-direction: column;
    min-height: 100vh;
}

.page-header {
    flex-shrink: 0;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    /* header样式 */
}

.page-footer {
    flex-shrink: 0;
    position: fixed;
    bottom: 0;
    left: 0;
    width: 100%;
    /* footer样式 */
}
</style>
