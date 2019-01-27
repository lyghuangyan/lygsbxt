<template>
    <div>
        <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-date"></i> 表单</el-breadcrumb-item>
                <el-breadcrumb-item>文件上传</el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="container">
			<div class="content-title">更新用户头像（支持裁剪）</div>
			<div class="crop-demo">
				<img :src="cropImg" class="pre-img">
				<div class="crop-demo-btn">选择图片
					<input class="crop-input" type="file" name="image" accept="image/*" @change="setImage"/>
				</div>
			</div>
            <div class="content-title">上传文件（支持拖拽）</div>
            <el-upload
                class="upload-demo"
                drag
                action="/jinge/upload/uploadImage"
                multiple>
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
            </el-upload>
            
        
            <el-dialog title="裁剪图片" :visible.sync="dialogVisible" width="30%">
				<!--:cropmove="cropImage" :zoom="cropImage" :ready="cropImage"-->
                <vue-cropper ref='cropper' 
				:aspectRatio="aspectRatio" 
				:minCropBoxWidth="minCropBoxWidth"
				:minCropBoxHeight="minCropBoxHeight"
					:src="imgSrc" style="width:100%;height:300px;">
					</vue-cropper>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="cancelCrop">取 消</el-button>
                    <el-button type="primary" @click="uploadFile()">确 定</el-button>
                </span>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    import VueCropper  from 'vue-cropperjs';
    export default {
        data: function(){
            return {
                //defaultSrc: './static/img/img.jpg',
                fileList: [],
                imgSrc: './static/img/img.jpg',
                cropImg: '',
                dialogVisible: false,
				file:'',
				imgCropperData: {
					accept: "image/gif, image/jpeg, image/png, image/jpg"
				},
				cropper:"",
				aspectRatio:1,
				minCropBoxWidth:100,
				minCropBoxHeight:100,
				fileName:''
            }
        },
        components: {
            VueCropper
        },
        methods:{
            setImage(e){
				var that = this;
                this.file = e.target.files[0];
                if (!this.file.type.includes('image/')) {
                    return;
                }
				this.fileName = this.file.name;
                const reader = new FileReader();
                reader.onload = (event) => {
                    this.dialogVisible = true;
                    this.imgSrc = event.target.result;
                    this.$refs.cropper && this.$refs.cropper.replace(event.target.result);
                };
                reader.readAsDataURL(this.file);
            },
            cropImage () {
                this.cropImg = this.$refs.cropper.getCroppedCanvas().toDataURL();
            },
            cancelCrop(){
                this.dialogVisible = false;
                //this.cropImg = this.defaultSrc;
            },
            imageuploaded(res) {
                console.log(res)
            },
            handleError(){
                this.$notify.error({
                    title: '上传失败',
                    message: '图片上传接口上传失败，可更改为自己的服务器接口'
                });
            },
			//调用后台接口上传文件
			uploadFile() {
				let that = this;
				this.$refs.cropper.getCroppedCanvas().toBlob((blob) => {
					let formdata = new FormData();
					console.info(that.fileName);
					formdata.append("file", blob, that.fileName);
					let config = {
						headers:{'Content-Type':'multipart/form-data'}
					};
					that.axios.post('/jinge/upload/uploadFile', formdata, config)
					.then(res => {
						console.info(res);
						var data = res.data;
						if(data.code == '200') {
							//关闭窗口
							that.dialogVisible = false;
							that.cropImg = '/jinge'+data.data.fileUrl;
							that.updateUserHead(data.data.id);
						} else {
							that.$message.error('上传失败！');
						}
						//var headurl
					});
				});
			},
			//更新用户头像
			updateUserHead(headId) {
				let that = this;
				let params = new FormData();
				params.append('headId',headId);
				this.axios.post('/jinge/sysuser/updateHeadId',params)
				.then(function(response){
					console.info(response.data);
					if(response.data.code == '200') {
						//Message.success("用户头像更新成功！");
						that.$message.success('用户头像更新成功！');
						//that.vHead.refreshUserInfo();
						
					}
				}).catch(function(error){
					console.info(error);
					//Message.error("服务器繁忙,请稍后重试");
					that.$message.error('服务器繁忙,请稍后重试');
				});
			}
		},
        created(){
            //this.cropImg = this.defaultSrc;
			var that = this;
			this.axios.get('/jinge/sysuser/getPersonalUserInfo')
			.then(function(response){
				console.info(response.data);
				if(response.data.code == '200') {
					//that.nickname = response.data.data.nickname;
					that.cropImg = '/jinge'+response.data.data.headurl;
				}
			}).catch(function(error){
				console.info(error);
				that.$message.error("服务器繁忙,请稍后重试");
			});
        }
    }
</script>

<style scoped>
    .content-title{
        font-weight: 400;
        line-height: 50px;
        margin: 10px 0;
        font-size: 22px;
        color: #1f2f3d;
    }
    .pre-img{   
        width: 100px;
        height: 100px;
        background: #f8f8f8;
        border: 1px solid #eee;
        border-radius: 5px;
    }
    .crop-demo{
        display: flex;
        align-items: flex-end;
    }
    .crop-demo-btn{
        position: relative;
        width: 100px;
        height: 40px;
        line-height: 40px;
        padding: 0 20px;
        margin-left: 30px;
        background-color: #409eff;
        color: #fff;
        font-size: 14px;
        border-radius: 4px;
        box-sizing: border-box;
    }
    .crop-input{
        position: absolute;
        width: 100px;
        height: 40px;
        left: 0;
        top: 0;
        opacity: 0;
        cursor: pointer;
    }
</style>