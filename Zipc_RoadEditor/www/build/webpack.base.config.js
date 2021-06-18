

const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
    //target: 'node',
    entry: {
        // 'app': './www/index.ts'
        editor: './index.js',
        preview: './preview.js'
    },
    output: {
        path: path.resolve(__dirname, '../../dist/static'),
        // filename: '[name].[chunkhash:8].js'
        filename: '[name].js'
    },
    resolve: {
        extensions: ['.js', '.ts', '.tsx']
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/i,
                loader: 'ts-loader',
                exclude: /node_modules/,
                options: {
                    onlyCompileBundledFiles: true,
                    // use the main tsconfig.json for all compilation
                    configFile: path.resolve(__dirname, "../tsconfig.json")
                }
            },
            {
                test: /\.css$/i,
                use: ['style-loader', 'css-loader'],
            },
            {
                test: /\.mjs$/i,
                include: /node_modules/,
                type: "javascript/auto"
            }
        ]
    },
    plugins: [
        new HtmlWebpackPlugin({
            template: './index.html'
        }),
        new webpack.ProvidePlugin({
            signals: 'signals',
            CodeMirror: 'codemirror',
            tern: 'ternjs',
            d3: 'd3',
            math: 'mathjs',
            Timeliner: path.resolve(__dirname, '../../vendors/js/libs/timeliner.js'),
            jsonlint: path.resolve(__dirname, '../../vendors/js/libs/jsonlint.js')

            //Timeliner: '../../../../vendors/js/libs/timeliner.js',
            //jsonlint: '../../../vendors/js/libs/jsonlint.js'

        })
    ],
    optimization: {
        splitChunks: {
            chunks: 'initial'
        }
    }
}