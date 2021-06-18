
const CopyWebpackPlugin = require('copy-webpack-plugin')
const { CleanWebpackPlugin } = require('clean-webpack-plugin')

module.exports = {
    plugins: [
        new CopyWebpackPlugin({
            patterns: [
                {
                    from: '*.css',
                    to: 'css/',
                    context: './css',
                },
                {
                    from: '*.*',
                    to: 'images/',
                    context: './images',
                },
                {
                    from: '*.*',
                    to: 'textures/',
                    context: './textures',
                },
                {
                    from: '*.json',
                    to: 'examples/',
                    context: './examples',
                },
                {
                    from: 'web.xml',
                    to: 'WEB-INF/',
                    context: './WEB-INF',
                }
            ],
        }),
        new CleanWebpackPlugin()
    ]
}