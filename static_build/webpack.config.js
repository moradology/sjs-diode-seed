'use strict';

var webpack = require('webpack');
var CommonsChunkPlugin = webpack.optimize.CommonsChunkPlugin;

module.exports = {

    entry: {
        index: './bundle.js',
    },
    output: {
        path: __dirname + '/../target/scala-2.11/classes',
        publicPath: "/../target/scala-2.11/classes/",
        filename: '[name]-bundle.js'
    },
    plugins: [
        new webpack.NoErrorsPlugin(),
        new CommonsChunkPlugin({
            name: "index"
        })
    ],
    module: {
        loaders: [
            {
                test: /\.css$/,
                loader: 'style-loader!css-loader'
            },

            {
                test: /\.(png|jpg|svg)$/,
                loaders: [
                    'url-loader?limit=8192',
                    'image-webpack?optimizationLevel=7&progressive=true']
            } // inline base64 URLs for <=8k images, direct URLs for the rest
        ]
    }


};
