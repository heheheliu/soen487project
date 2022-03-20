<template>
  <div class="log">
    <el-container>
      <el-header>
        <el-row>
          <el-col :span="2" class="back">
            <i class="el-icon-d-arrow-left" @click="back"></i>
          </el-col>
          <el-col :span="20" style="text-align: center">
            <h1>Check the Log</h1>
          </el-col>
        </el-row>
      </el-header>
      <el-main>
        <el-row>
          <el-col :span="6">
            <span>From  </span>
            <el-date-picker
              v-model="yearFrom"
              value-format="yyyy"
              type="year"
              placeholder="Pick a year">
            </el-date-picker>
          </el-col>
          <el-col :span="6">
            <span>To  </span>
            <el-date-picker
              v-model="yearTo"
              value-format="yyyy"
              type="year"
              placeholder="Pick a year">
            </el-date-picker>
          </el-col>
          <el-col :span="6">
            <span>Type  </span>
            <el-select v-model="typeLog" clearable placeholder="Select">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-col>
          <el-col :span="6">
            <el-button icon="el-icon-search" circle @click="searchLog"></el-button>
          </el-col>
        </el-row>
        <el-row v-for="l in log" :key="l.timestamp">
          <el-tag :type="selectType(l.type.__text)" effect="dark">
            <span>Code: {{ l.isrc.__text }}  *  </span>
            <span>Type: {{ l.type.__text }}  *  </span>
            <span>Time: {{ l.timeStamp.__text }}</span>
          </el-tag>
        </el-row>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import X2JS from 'x2js';

export default {
  name: "AddAlbum",
  data() {
    return {
      log: "",
      show: null,
      yearFrom: "",
      yearTo: "",
      typeLog: "",
      options: [{
          value: 'update',
          label: 'UPDATE'
        }, {
          value: 'create',
          label: 'CREATE'
        }, {
          value: 'delete',
          label: 'DELETE'
      }],

    }
  },
  created() {

  },
  methods: {
    back() {
      this.$router.back(-1);
    },
    searchLog() {
      if((this.yearFrom==""&&this.yearTo==""&&this.typeLog=="")||(this.yearFrom&&this.yearTo&&this.typeLog)) {
        let xmlBody = `
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
                      xmlns:us="http://www.soen487.com/log-ws">
        <soapenv:Header/>
        <soapenv:Body>
            <us:getChangeLogsRequest>
                <us:from>${this.yearFrom}</us:from>
                <us:to>${this.yearTo}</us:to>
                <us:type>${this.typeLog}</us:type>
            </us:getChangeLogsRequest>
        </soapenv:Body>
        </soapenv:Envelope>
        `;
        axios.post(
          "http://localhost:8866/log",
          xmlBody,
          {
            headers: {
              "Content-Type": "text/xml"
            }
          }
        ).then((res)=>{
          var x2js = new X2JS();
          this.show = x2js.xml2js(res.data);
          this.log = JSON.parse(JSON.stringify(this.show));
          this.log = this.log.Envelope.Body.getChangeLogsResponse.changedLogs.list;
        });
      } else {
        this.$notify.error({
          title: 'Error',
          message: 'Invalid Input'
        });
      }
    },
    selectType(type) {
      if(type=="UPDATE") {
        return "";
      }
      if(type=="DELETE") {
        return "danger";
      }
      if(type=="CREATE") {
        return "success";
      }
    }
  },
};
</script>

<style scoped>
.log {
  height: 100%;
}

.el-container {
  background-color: #F0DAD2;
  height:100%;
}

.item {
  margin-bottom: 10px;
}

.back {
  cursor: pointer;
}

.el-row {
  padding: 10px;
  margin-bottom: 10px;
}
</style>