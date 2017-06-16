<template>
  <table style="border-spacing: 0px">
    <div class="form-group"   :class="{'has-error': emptyField || errorField }">
      <label>{{labelText}}</label>
      <div class="password" :class="{ 'control': true }">
        <tr><td style="width: 500px;">
          <i class="glyphicon glyphicon-lock "></i>
          <span @click="handleClick" v-show="!show && !emptyField" class="glyphicon glyphicon-eye-open " style="margin-right: 5%;"> </span>
          <span @click="handleClick" v-show="show && !emptyField" class="glyphicon glyphicon-eye-close " style="margin-right: 5%;"> </span>
          <input type="password"
                 v-show="!show"
                 :value="textValue"
                 :id="label"
                 tabindex="2"
                 class="form-control"
                 placeholder="••••••"
                 onfocus="this.placeholder = ''"
                 onblur="this.placeholder = '••••••'"
                 @focus="handleFocus" @blur="handleBlur"
                 @input="updateValue($event.target.value)">
          <input type="text"
                 v-show="show"
                 :value="textValue"
                 :id="label"
                 tabindex="2"
                 class="form-control"
                 @focus="handleFocus" @blur="handleBlur"
                 @input="updateValue($event.target.value)">
        </td>
          <td>
            <span v-show="isValid" class="glyphicon glyphicon-ok-circle color-green"></span>
            <span v-show="isNotValid" class="glyphicon glyphicon-remove-circle color-red"></span>
          </td>
        </tr>
        <tr><td style="height: 20px;">
          <span v-show="emptyField" class="color-red ">{{labelText}} est obligatoire</span>
          <span v-show="errorField && !emptyField" class="color-red">{{errorMessage}}</span>
        </td></tr>
      </div>
    </div>
  </table>
</template>

<script>
  export default {
      name: 'customPasswordInput',
    props: ['value', 'label', 'labelText', 'emptyField', 'errorField', 'errorMessage', 'show', 'isValid', 'isNotValid'],
    data(){
          return {
            textValue: this.value
          }
    },
    methods: {
      updateValue(value){
        this.textValue = value;
        this.$emit('input', value);
      },
      handleFocus(){
        this.$emit('focus');
      },
      handleBlur(){
        this.$emit('blur');
      },
      handleClick(){
        this.$emit('click');
      }
    }
  }
</script>
