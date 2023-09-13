const path = require('path');
const webpack = require('webpack');
const outputDir = path.resolve(__dirname, "build");

module.exports = {
    mode: "development",
    entry: [path.resolve(__dirname, "./src/main/resources/static/es/jquery.js")],
    resolve: {
        alias: {
            'jquery': require.resolve('jquery')
        }
    }, devtool: "inline-source-map",
    output: {
        path: outputDir,
        filename: "[name].js"
    }, module: {rules: [{test: /\.js$/i, exclude: /node_modules/, loader: "babel-loader"}]},
    plugins: [new webpack.ProvidePlugin({$: 'jquery', jQuery: 'jquery', jquery: 'jquery'})]
}
