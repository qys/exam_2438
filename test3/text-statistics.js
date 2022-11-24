const csv = require('csvtojson')
// 引入核心模块(fs)
var fs = require('fs')
const {resolve} = require('path')
 
// 获取命令行第一个参数，即文件
const arg1 = process.argv.slice(2,3)
//读取csv文件
csv().fromFile(arg1[0]).then((jsonObj) => {
    console.log(jsonObj.length)

    // jsonObj = jsonObj.filter((obj, index) => {
    //     return index > 10000 ? false : true;
    // })

    // 获取所有的城市、年份、车型
    const citySet = new Set()
    const yearSet = new Set()
    const modelSet = new Set()
    jsonObj.forEach((obj) => {
        citySet.add(obj.City)
        yearSet.add(obj['Model Year'])
        modelSet.add(obj['electic vehicle type'])
    })
    // console.log(citySet)
    // console.log(yearSet)
    // console.log(modelSet)
    // console.log(vinSet)

    //按条件过滤
    //"state","city","year","electic vehicle type","count"
    var outArr = new Array()

    var cityYearModelArr = [
        // {
        //     city: [
        //         {
        //             year: [
        //                 {
        //                     model: [

        //                     ]
        //                 }
        //             ]
        //         }
        //     ]
        // }
    ]

    citySet.forEach((city) => {
        var cityArr = {city: [], name: city} 
        yearSet.forEach((year) => {
            var yearArr = {year: [], name: year}
            modelSet.forEach((model) => {
                var modelArr = {model: [], name: model}
                yearArr.year.push(modelArr)
            })
            cityArr.city.push(yearArr)
        })
        cityYearModelArr.push(cityArr)
    })

    jsonObj.forEach((obj, index) => {
        cityYearModelArr.forEach((cityObj, index) => {
            if (obj.City == cityObj.name) {
                cityObj.city.forEach((yearObj) => {
                    if (obj['Model Year'] == yearObj.name) {
                        yearObj.year.forEach((modelObj) => {
                            if (obj['electic vehicle type'] == modelObj.name) {
                                // 加入数组
                                modelObj.model.push(obj)
                            }
                        })
                    }
                })
            }
        })
    })

    cityYearModelArr.forEach((cityObj) => {
        cityObj.city.forEach((yearObj) => {
            yearObj.year.forEach((modelObj) => {
                if (modelObj.model.length > 0) {
                    outArr.push(
                        [
                            modelObj.model[0].State,
                            cityObj.name,
                            yearObj.name,
                            getNewType(modelObj.model[0]['Electric Vehicle Type']),
                            modelObj.model.length
                        ]
                    )
                }
            })
        })
    })
    
    var writeStr = getWriteString(outArr)

    writeFile(writeStr)
})

function getNewType(str) {
    return str.substring(str.indexOf('(') + 1, str.indexOf(')'))
}

function getWriteString(outArr) {
    var str = '"state","city","year","electic vehicle type","count"' + '\n'
    outArr.forEach((objArr) => {
        var tmp = '';
        for(let i in objArr) {
            if (i == objArr.length - 1) {
                tmp += objArr[i]
            } else {
                tmp += objArr[i] + ','
            }
        }
        tmp += '\n'
        str += tmp
    })
    return str
}
//创建统计结果文件
function writeFile(str) {
    // // API
    fs.writeFile('./test3_output.txt', str, (error) => {
    
        // 创建失败
        if(error){
            console.log(`创建失败：${error}`)
        }

        // 创建成功
        console.log(`创建成功！`)
        
    })
// 注意：同名/同类型文件多次创建并不会新增，而是覆盖机制
}



