const path = require('path');
const CaseSensitivePathsWebpackPlugin = require('case-sensitive-paths-webpack-plugin');
module.exports = function(env) {
    return {
        mode: "none",
        entry: path.resolve(`src/index.js`),
        output: {
            path: path.resolve('../backend/src/main/resources'),
            filename: 'assets/js/main.js',
            assetModuleFilename: 'assets/images/[hash][ext]'
        },
        module:{
            rules : [{
                test: /\.json/i,
                type: 'javascript/auto',
                loader:'json-loader'
            },{
                test: /\.js/i,
                exclude: /node_modules/,
                loader: 'babel-loader', 
                options: {
                    configFile: path.resolve('config/babel.config.json')
                }
            },{
                // .css|.scss|.sass로 끝나는 파일이름 빌드할때. 로더사용.
                test: /\.(css|scss|sass)$/i,
                use: [
                    'style-loader',
                    {
                        loader:'css-loader',
                        options: {
                            modules: true
                        }
                    },
                    'sass-loader']
            }, {
                test: /\.(png|jp?eg|gif|svg|icon|tif?f|bmp)/i,
                type: 'asset/resource'
            }]
        },
        plugins:[
            new CaseSensitivePathsWebpackPlugin()
        ],
        devtool: "eval-source-map",
        devServer:{
            host: '0.0.0.0',
            port: 9090,
            liveReload: true,
            compress: true,
            hot: false,
            proxy:{
                '/api':'http://localhost:8080'
            },
            static:{
                directory: path.resolve('./public')
            },
            historyApiFallback:true  
        }
    
    };
}