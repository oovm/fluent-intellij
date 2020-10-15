package com.github.projectfluent.ide.highlight

import com.github.projectfluent.FluentBundle
import com.github.projectfluent.ide.file_view.JssIcons
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class JssColorSettingsPage : ColorSettingsPage {
    private val annotatorTags = JssColor
        .values()
        .associateBy({ it.name }, { it.textAttributesKey })

    override fun getAttributeDescriptors() = JssColor
        .values()
        .map { it.attributesDescriptor }
        .toTypedArray()

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = com.github.projectfluent.FluentBundle.message("filetype.name")

    override fun getIcon() = JssIcons.FILE

    override fun getHighlighter() = JssSyntaxHighlighter()

    override fun getAdditionalHighlightingTagToDescriptorMap() = annotatorTags

    override fun getDemoText() =
        """/// A product from Acme's catalog
schema Product: object {
    ${"$"}schema: https://json-schema.org/draft/2020-12/schema
    ${"$"}id: https://example.com/product.schema.json
    "required": [
        "productId",
        "productName",
        "price"
    ]
}

/// The unique identifier for a product
properties productId: integer {

}

/// Name of the product
properties productName: string {

}

/// The price of the product
properties productName: number {
    exclusiveMinimum: 0
}

/// Tags for the product
properties tags: array {
    minItems: 1,
    uniqueItems: true
    "items": {
        "type": "string"
    },
}

/// Tags for the product
properties dimensions: object {
    .length: number
    .width: number
    .height: number

    required: ["length", "width", "height"]
}
"""


}
