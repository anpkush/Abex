package com.example.abexapp.retrofit.model

data class PermitResponse(
    val sections: List<Section>,
    val errorMessage: Any
) {
    data class Section(
        val title: String,
        val permitStatus: Any,
        val subtitle: String,
        val fields: List<Field>,
        val grids: List<Any>,
        val sections: List<Any>,
        val errorMessage: Any,
        val twoColumnType: Any,
        val expandable: Boolean,
        val expanded: Boolean
    ) {
        data class Field(
            val name: String,
            val type: String,
            val tableName: String,
            val returnColumn: String,
            val condition: List<String>,
            val listOfElements: Any,
            val radioButtonValue: Any,
            val selectedValues: List<Any>,
            val required: Boolean,
            val editable: Boolean,
            val readonly: Boolean,
            val description: String,
            val textValue: Any,
            val optionsAvailable: List<String>,
            val selectedValue: Any,
            val timeValue: String,
            val activeButtonsText: Any,
            val activeButtonActions: Any,
            val deactivateButtonText: Any,
            val alertName: Any,
            val action: Any
        )
    }
}