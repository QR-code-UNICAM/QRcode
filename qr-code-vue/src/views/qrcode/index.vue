<template>
  <el-container>
    <el-header>

        <el-tabs v-model="TabValue" type="card" @tab-click="TabClick">
          <el-tab-pane disabled="true">
            <span slot="label"><i class="el-icon-s-grid" style="width:25px ;height: auto "></i><b style="color: #696969">QRCode</b></span>
          </el-tab-pane>
          <el-tab-pane label="QR Code Generator" name="first">

            <el-container>
              <el-aside width="800px">



                <el-form ref="sizeForm" :model="sizeForm" enctype="multipart/form-data"  label-width="80px" size="medium">
                  <!--          <el-form ref="sizeForm" :model="sizeForm"   label-width="80px" size="medium">-->

                  <el-tabs v-model="activeName" @tab-click="clearText" >
                    <el-tab-pane label="TEXT" name="text">
                      <el-form-item label="TEXT">
                        <el-input type="textarea" :rows="2" v-model="sizeForm.text" placeholder="Paste enter text to create QR code" width="300px"></el-input>
                      </el-form-item>
                    </el-tab-pane>
                    <el-tab-pane label="URL" name="url">
                      <el-form-item label="URL">
                        <el-input  v-model="sizeForm.text" placeholder="Paste a url to create QR code" width="300px"></el-input>
                      </el-form-item>
                    </el-tab-pane>

                    <el-tab-pane label="Picture" name="picture">
                      <el-form-item label="TEXT">
                        <el-input type="textarea" :rows="2" v-model="sizeForm.text" placeholder="Paste a url or enter text to create QR code" width="300px"></el-input>
                      </el-form-item>
                      <el-form-item label="uploadPhoto">
                        <input ref="fileInput" type="file" name="file" @change="handleFileChange">
                      </el-form-item>
                    </el-tab-pane>
                    <!--                  <el-tab-pane label="File" name="file">-->
                    <!--                    <el-form-item label="TEXT">-->
                    <!--                      <el-input type="textarea" :rows="2" v-model="sizeForm.text" placeholder="Paste enter text to create QR code" width="300px"></el-input>-->
                    <!--                    </el-form-item>-->
                    <!--                    <el-form-item label="uploadPhoto">-->
                    <!--                      <input ref="fileInput" type="file" name="file" @change="handleFileChange">-->
                    <!--                    </el-form-item>-->
                    <!--                  </el-tab-pane>-->
                  </el-tabs>


                  <el-form-item label="qrCodeType">
                    <el-radio-group v-model="sizeForm.flag" size="medium">
                      <el-radio border label="normal"></el-radio>
                      <el-radio border label="color"></el-radio>
                      <el-radio border label="style"></el-radio>
                      <el-radio border label="logo"></el-radio>
                      <el-radio border label="background"></el-radio>
                      <!--                    <el-radio border label="imageFill"></el-radio>-->
                      <el-radio border label="gif"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                  <el-form-item label="qrCodeColor">
                    <el-color-picker v-model="sizeForm.color" ></el-color-picker>
                  </el-form-item>
                  <el-form-item size="large">
                    <el-button type="primary" @click="onSubmit">Generating a QRCode</el-button>
                    <el-tag type="info"><button type="reset">Reset</button></el-tag>
                  </el-form-item>
                </el-form>

              </el-aside>

              <el-main>
                <div>
                    <el-dialog :visible.sync="dialogVisible">
                      <img  :src="qrcode" style="width: 500px ;height: auto">
                    </el-dialog>
                  <el-button type="text"  @click="showEnlargeImage">Click to enlarge</el-button>
                </div>
              </div>
            </el-card>
          </div>

          <el-form ref="sizeForm" :model="sizeForm" enctype="multipart/form-data"  label-width="80px" size="medium">
<!--          <el-form ref="sizeForm" :model="sizeForm"   label-width="80px" size="medium">-->
            <el-form-item label="URL OR TEXT">
              <el-input v-model="sizeForm.text" placeholder="Paste a url or enter text to create QR code" width="300px"></el-input>
            </el-form-item>

            <el-form-item label="uploadPhoto">
              <input ref="fileInput" type="file" name="file" @change="handleFileChange">
            </el-form-item>

            <el-form-item label="qrCodeType">
              <el-radio-group v-model="sizeForm.flag" size="medium">
                <el-radio border label="normal"></el-radio>
                <el-radio border label="color"></el-radio>

                <el-radio border label="style"></el-radio>
                <el-radio border label="logo"></el-radio>
                <el-radio border label="background"></el-radio>
                <el-radio border label="imageFill"></el-radio>
                <el-radio border label="gif"></el-radio>
              </el-radio-group>
            </el-form-item>
            <el-form-item label="qrCodeColor">
            <el-color-picker v-model="sizeForm.color" ></el-color-picker>
            </el-form-item>
            <el-form-item size="large">
              <el-button type="primary" @click="onSubmit">Create Instantly</el-button>
              <el-tag type="info"><button type="reset">Reset</button></el-tag>
            </el-form-item>
          </el-form>


        </el-tab-pane>
        <el-tab-pane label="QR Code History" name="second">
          <div class="block">
            <span class="demonstration">QR Code History</span>
            <el-carousel :interval="4000" type="card" height="300px">
              <el-carousel-item v-for="item in qrcodeHistory" :key="item.name">
                <img :src="item.qrcode">
              </el-carousel-item>
            </el-carousel>
            <div>

            </div>
          </div>
        </el-tab-pane>
<!--        <el-tab-pane label="More" name="third">-->
<!--          More-->
<!--        </el-tab-pane>-->
        <el-tab-pane label="LOGOUT" name="LOGOUT">
          LOGOUT
        </el-tab-pane>

      </el-tabs>

    </el-header>




  </el-container>


</template>

<script>



  import {createCode,codeList} from "@/api/login";

  export default {

    created() {
      console.log(this.$route)


    },
    data() {
      return {
        TabValue: 'first',
        text: '',
        previewPhoto: '',
        qrcode:'https://th.bing.com/th/id/R.bcd18b2eff79aa76c210cb1b4fa9e718?rik=TWBJEYvTNbweXA&riu=http%3a%2f%2fpic.616pic.com%2fys_bnew_img%2f00%2f10%2f41%2fe0SAYdMOs1.jpg&ehk=o1kc%2fcW7P17uacN%2fn%2fqoQKqoYYaY3TmPdLFC0lXqfkM%3d&risl=&pid=ImgRaw&r=0',
        qrcodeHistory:[],
        dialogVisible:false,
        sizeForm: {
          text: '',
          flag: '',
          file: '',
          color:'#FBFBFB',
        }

      }
    },

    filters: {
      formatType(type) {
        // capitalize type
        return type.charAt(0).toUpperCase() + type.slice(1)
      }
    },

    methods: {
      onSubmit() {

        const formData = new FormData()

// 文件对象
        formData.append('file', this.sizeForm.file)

// 普通文本数据
        formData.append('text', this.sizeForm.text)
        formData.append('flag',this.sizeForm.flag)
        formData.append('user', this.$route.query.email)


        if (this.sizeForm.flag == 'color'){
          let color = this.sizeForm.color
          console.log(color)
          let rgb = this.hexToRgbString(color)
          // 更新表单的值
          this.sizeForm.color = rgb
          console.log(rgb)
          formData.append('color', rgb)
        }



        return new Promise((resolve, reject) => {
          createCode(formData).then(res => {
            console.log(res.data)
            console.log(res.data.creattime)

            this.qrcode = "data:image/jpeg;base64,"+res.data.qrcode;

          }).catch(error => {
            reject(error)
            alert("Upload Failed")
          })
        })

      },
      hexToRgbString(hex) {
        let r = parseInt(hex.slice(1, 3), 16)
        let g = parseInt(hex.slice(3, 5), 16)
        let b = parseInt(hex.slice(5, 7), 16)

        return `${r},${g},${b}`
      },
      showEnlargeImage() {
        this.dialogVisible = true
      },
      handleFileChange(e) {
         this.sizeForm.file = e.target.files[0]
      },
      TabClick(tab) {
        if (tab.name === 'second'){
          const email = this.$route.query.email
          return new Promise((resolve, reject) => {

            codeList(email).then(res => {

              console.log(res.data)

              // this.qrcodeHistory = "data:image/jpeg;base64,"+ res.data.qrcode
              // 处理每个对象
              res.data.forEach(item => {
                this.qrcodeHistory.push({
                  qrcode: "data:image/jpeg;base64,"+item.qrcode,
                  name: item.id
                  })
                })

            }).catch(error => {
              reject(error)
            })
          })
        }

        if (tab.name == 'LOGOUT'){
          this.$router.push({path:'/login'});
        }


      }

    }
  }
</script>
<style>

img {
  width: 300px;
  height: 300px;
}

.el-dialog {
  width: 500px;
  height: 500px;
}

.el-dialog__body {
  width: 500px;
  height: 500px;

}

.el-header {
  background-color: #ffffff;
  color: #00afff;
  text-align: center;
  line-height: 60px;
}
.el-carousel__item h3 {
  color: #64758a;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #ffffff;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #9ea6ad;
}


</style>
