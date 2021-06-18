

const webpack = require('webpack')

module.exports = {
    devtool: false,
    plugins: [
        new webpack.SourceMapDevToolPlugin({
            filename: "[file].map",
            fallbackModuleFilenameTemplate: '[absolute-resource-path]',
            moduleFilenameTemplate: '[absolute-resource-path]'
        })
    ]
}