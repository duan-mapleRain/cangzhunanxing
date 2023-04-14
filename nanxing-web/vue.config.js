module.exports = ({
    pages: {
        index: {
            entry: 'src/main.js',
            template: 'public/index.html',
            filename: 'index.html'
        }
    },
    devServer: {
        host: 'localhost',
        port: 8081,
        open: 'Main.html'
    },
    lintOnSave:false
})
