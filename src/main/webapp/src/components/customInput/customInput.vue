<template>
  <table style="border-spacing: 0px">
    <div class="form-group">
      <label>{{labelText}}</label>
      <div class="inner-addon left-addon"
           :class="{ 'has-error' : emptyField || existField || errorField }">
        <tr><td style="width: 500px;">
          <i class="glyphicon" :class="icon"></i>
          <input v-if="type=='text'"
                 :ref="label"
                 type="text"
                 :id="label"
                 :v-el="label"
                 :tabindex="tab"
                 class="form-control"
                 :placeholder="placeholder"
                 @focus="handleFocus" @blur="handleBlur"
                 @input="updateValue($event.target.value)"
                 :value="value"
                 onblur="this.placeholder = placeholder "
                 maxlength="maxlength"
                 minlength="minlength"
                 :disabled = "disabled">
          <input v-if="type=='email'"
                 type="email"
                 :id="label"
                 :tabindex="tab"
                 class="form-control"
                 :value="value"
                 :placeholder="placeholder"
                 @focus="handleFocus" @blur="handleBlur"
                 @input="updateValue($event.target.value)"
                 onblur="this.placeholder = placeholder " >
        </td></tr>
        <tr><td style="height: 20px;">
          <span v-show="emptyField" class="color-red">{{labelText}} est obligatoire</span>
          <span v-show="existField" class="color-red ">{{existMessage}}</span>
          <span v-show="errorField" class="color-red">{{ errorMessage }}</span>
        </td></tr>
      </div>
    </div>
  </table>
</template>

<script>
  export default {
      name: 'customInput',
    props: ['value', 'label', 'labelText', 'icon', 'type', 'tab', 'placeholder', "maxlength",
      "minlength", 'emptyField', "existField", "existMessage", "errorField", "errorMessage", "disabled"],
      data() {
          return {
            show: false
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

    }
  }
</script>
