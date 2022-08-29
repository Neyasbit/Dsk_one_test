package com.example.core.data.fake

import com.example.core.data.model.DskResponceItem
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.intellij.lang.annotations.Language

object FakeDataSource {

    @Language("JSON")
    val data = """
       [
           {
               "id": 64,
               "slug": "1-lermontovskij",
               "title": "1-й Лермонтовский",
               "class": 2,
               "propertyClass": 2,
               "flatType": 0,
               "min_area": 1.4,
               "max_area": 350.7,
               "is_active": null,
               "file_id": 1373082,
               "file": {
                   "id": 1373082,
                   "file_name": "616714eecd041_ae8e7625458fd18bc18974cf12859e49.png",
                   "is_img": true,
                   "title": null,
                   "description": null,
                   "sort": -1,
                   "created_at": "2020-09-01 09:00:06",
                   "updated_at": "2021-10-13 17:18:38",
                   "type": "image/png",
                   "original_name": "лермонтовский.png",
                   "bucket": "fsk-uploads"
               },
               "header_color": null,
               "hover_color": null,
               "cost_from": "",
               "lat": 55.680674164023,
               "lng": 37.909532784162,
               "city_id": 1,
               "new_view": false,
               "post_address": "Московская область, г.о. Люберцы",
               "img": "https://fsk-uploads.hb.bizmrg.com/production/616714eecd041_ae8e7625458fd18bc18974cf12859e49.png",
               "img_single": "https://fsk-uploads.hb.bizmrg.com/production/616714ef0bf44_ae8e7625458fd18bc18974cf12859e49.png",
               "img_double": "https://fsk-uploads.hb.bizmrg.com/production/62d956693d13f_95fa68613b3164af9d951bd7d6dfe1a4.jpg",
               "tile_size": 1,
               "transport": {
                   "from": "Некрасовка",
                   "color": "#e09ebf",
                   "route": [
                       {
                           "type": "auto",
                           "time": 5
                       }
                   ]
               },
               "offer": 27,
               "flats": {
                   "built": 195,
                   "furniture": 0,
                   "finish": 656,
                   "booking": 655,
                   "all": 656
               },
               "flatsHasSalePrice": true,
               "minPriceBuilt": 6876030,
               "price_from": 5542000,
               "price_to": 18941040,
               "area_from": 23,
               "area_to": 109,
               "build_to": "2024-06-30",
               "build_from": "2022-08-02",
               "build": [
                   "2022-08-02",
                   "2022-08-03",
                   "2023-09-30",
                   "2024-06-30"
               ],
               "sidebar": {
                   "flats": true,
                   "parking": true,
                   "pantry": true,
                   "map": true,
                   "decoration": true,
                   "furniture": false,
                   "smart": false,
                   "progress": true,
                   "document": true,
                   "promo": true,
                   "mortgage": true,
                   "news": true,
                   "contacts": true,
                   "apartments": false,
                   "apart-hotel": false,
                   "installment_plan": true,
                   "netting": true,
                   "military_mortgage": true,
                   "maternal_capital": true,
                   "subsidies": false,
                   "evropeiskaya": false
               },
               "rooms": [
                   0,
                   1,
                   2,
                   3,
                   4
               ],
               "dsk": true,
               "labels": [
                   {
                       "title": "Скидки до 15%",
                       "color": "#cf2e27",
                       "src": "",
                       "link": "",
                       "link-dsk": ""
                   },
                   {
                       "title": "Ипотека 0,01%",
                       "color": "#e94200",
                       "src": "",
                       "link": "",
                       "link-dsk": ""
                   }
               ],
               "mortgages": [
                   0,
                   1,
                   2,
                   4,
                   5,
                   6,
                   7,
                   8
               ],
               "uuid": "",
               "type_flat": 1,
               "group-flat": false,
               "decoration": [
                   10,
                   30
               ],
               "vdsk": false,
               "decorationTitle": "<p>Квартира с отделкой White Box или с одним из четырех стильных дизайнерских решений? Выбирать Вам!</p>\r\n",
               "decorationDescription": "",
               "launch_announcement": false,
               "logo": "",
               "sites": [
                   "dsk"
               ],
               "hide_on_index": false,
               "finishconf": false,
               "flatFeatures": [
                   "000000006",
                   "000000009",
                   "000000013",
                   "000000016",
                   "000000027",
                   "000000036",
                   "000000052",
                   "000000056",
                   "000000093"
               ],
               "sort": 19
           },
           {
               "id": 71,
               "slug": "1-leningradskij",
               "title": "1-й Ленинградский",
               "class": null,
               "propertyClass": null,
               "flatType": 0,
               "min_area": 1.6,
               "max_area": 166.5,
               "is_active": null,
               "file_id": 1403721,
               "file": {
                   "id": 1403721,
                   "file_name": "61520ff8d96db_c5fd11201f84ea674e8f1ea5123623c0.jpg",
                   "is_img": true,
                   "title": null,
                   "description": null,
                   "sort": -1,
                   "created_at": "2021-03-09 12:18:38",
                   "updated_at": "2021-09-27 18:39:52",
                   "type": "image/jpeg",
                   "original_name": "ленинградский_мал.jpg",
                   "bucket": "fsk-uploads"
               },
               "header_color": null,
               "hover_color": null,
               "cost_from": "",
               "lat": 55.925400610418,
               "lng": 37.392302731907,
               "city_id": 1,
               "new_view": false,
               "post_address": "Москва, Молжаниновский район, Ленинградское шоссе, рядом с домом 236",
               "img": "https://fsk-uploads.hb.bizmrg.com/production/61520ff8d96db_c5fd11201f84ea674e8f1ea5123623c0.jpg",
               "img_single": "https://fsk-uploads.hb.bizmrg.com/production/61520ff93e58e_c5fd11201f84ea674e8f1ea5123623c0.jpg",
               "img_double": "https://fsk-uploads.hb.bizmrg.com/production/61520ff94aa54_247a542cf0d9e335f05dc334e8d0e3a5.jpg",
               "tile_size": 1,
               "transport": {
                   "from": "Молжаниново",
                   "color": "#303337",
                   "route": [
                       {
                           "type": "foot",
                           "time": 15
                       }
                   ]
               },
               "offer": 20,
               "flats": {
                   "built": 0,
                   "furniture": 0,
                   "finish": 431,
                   "booking": 431,
                   "all": 431
               },
               "flatsHasSalePrice": true,
               "minPriceBuilt": 0,
               "price_from": 7370000,
               "price_to": 20204105,
               "area_from": 24,
               "area_to": 109,
               "build_to": "2024-03-30",
               "build_from": "2023-03-31",
               "build": [
                   "2023-03-31",
                   "2023-06-30",
                   "2023-10-27",
                   "2024-03-30",
                   null
               ],
               "sidebar": {
                   "flats": true,
                   "parking": false,
                   "pantry": true,
                   "map": true,
                   "decoration": false,
                   "furniture": false,
                   "smart": false,
                   "progress": true,
                   "document": true,
                   "promo": true,
                   "mortgage": true,
                   "news": true,
                   "contacts": true,
                   "apartments": false,
                   "apart-hotel": false,
                   "installment_plan": false,
                   "netting": true,
                   "military_mortgage": true,
                   "maternal_capital": true,
                   "subsidies": false,
                   "evropeiskaya": true
               },
               "rooms": [
                   0,
                   1,
                   2,
                   3,
                   4
               ],
               "dsk": true,
               "labels": [
                   {
                       "title": "Скидки до 16%",
                       "color": "#cf2e27",
                       "src": "",
                       "link": "",
                       "link-dsk": ""
                   },
                   {
                       "title": "Ипотека 0,01%",
                       "color": "#e94200",
                       "src": "",
                       "link": "",
                       "link-dsk": ""
                   }
               ],
               "mortgages": [
                   0,
                   1,
                   2,
                   4,
                   5,
                   6,
                   7,
                   8
               ],
               "uuid": "279e085a-cb6e-4d2a-a5ef-00f6713378da",
               "type_flat": 1,
               "group-flat": false,
               "decoration": [
                   10,
                   30
               ],
               "vdsk": false,
               "decorationTitle": "<p>Если хочется поскорее переехать по новому адресу, можно выбрать квартиру с отделкой. В&nbsp;&laquo;1-м Ленинградском&raquo; 4 варианта готовой дизайнерской отделки. А для любителей интерьеров в&nbsp;индивидуальном стиле есть квартиры с отделкой White box.</p>\r\n",
               "decorationDescription": "",
               "launch_announcement": false,
               "logo": "",
               "sites": [
                   "dsk"
               ],
               "hide_on_index": false,
               "finishconf": false,
               "flatFeatures": [
                   "000000006",
                   "000000009",
                   "000000027",
                   "000000036",
                   "000000052",
                   "000000093",
                   "000000094"
               ],
               "sort": 20
           },
           {
               "id": 35,
               "slug": "uznaa-bitca",
               "title": "Южная Битца",
               "class": 2,
               "propertyClass": 2,
               "flatType": 0,
               "min_area": 1.5,
               "max_area": 929.8,
               "is_active": null,
               "file_id": 473817,
               "file": {
                   "id": 473817,
                   "file_name": "60b632677d625_312af0d810540cd3da2a5566c388498b.jpg",
                   "is_img": true,
                   "title": null,
                   "description": null,
                   "sort": -1,
                   "created_at": "2018-12-21 11:17:02",
                   "updated_at": "2021-06-01 13:13:11",
                   "type": "image/jpeg",
                   "original_name": "Битца_маленькая-_840-1080.jpg",
                   "bucket": "fsk-uploads"
               },
               "header_color": null,
               "hover_color": null,
               "cost_from": "",
               "lat": 55.555990426791,
               "lng": 37.597025630927,
               "city_id": 1,
               "new_view": false,
               "post_address": "Московская обл., Ленинский район, с/п Булатниковское, пос. Битца",
               "img": "https://fsk-uploads.hb.bizmrg.com/production/60b632677d625_312af0d810540cd3da2a5566c388498b.jpg",
               "img_single": "https://fsk-uploads.hb.bizmrg.com/production/60b63267a2103_312af0d810540cd3da2a5566c388498b.jpg",
               "img_double": "https://fsk-uploads.hb.bizmrg.com/production/62d9564d496d6_3a1bada50a824dd96431fa5225e587fa.jpg",
               "tile_size": 1,
               "transport": {
                   "from": "Аннино",
                   "color": "#acadaf",
                   "route": [
                       {
                           "type": "auto",
                           "time": 10
                       }
                   ]
               },
               "offer": 23,
               "flats": {
                   "built": 341,
                   "furniture": 0,
                   "finish": 527,
                   "booking": 339,
                   "all": 528
               },
               "flatsHasSalePrice": true,
               "minPriceBuilt": 6335253,
               "price_from": 6335253,
               "price_to": 17613270,
               "area_from": 22,
               "area_to": 109,
               "build_to": "2024-09-30",
               "build_from": "2020-12-23",
               "build": [
                   "2020-12-23",
                   "2021-05-25",
                   "2021-12-30",
                   "2022-03-30",
                   "2022-04-01",
                   "2023-09-30",
                   "2024-09-30",
                   null
               ],
               "sidebar": {
                   "flats": true,
                   "parking": true,
                   "pantry": true,
                   "map": true,
                   "decoration": true,
                   "furniture": false,
                   "smart": false,
                   "progress": true,
                   "document": true,
                   "promo": true,
                   "mortgage": true,
                   "news": true,
                   "contacts": true,
                   "apartments": false,
                   "apart-hotel": false,
                   "installment_plan": true,
                   "netting": true,
                   "military_mortgage": true,
                   "maternal_capital": true,
                   "subsidies": false,
                   "evropeiskaya": true
               },
               "rooms": [
                   0,
                   1,
                   2,
                   3,
                   4
               ],
               "dsk": true,
               "labels": [
                   {
                       "title": "Ипотека 0,01%",
                       "color": "#e94200",
                       "src": "",
                       "link": "",
                       "link-dsk": ""
                   },
                   {
                       "title": "Старт продаж нового корпуса",
                       "color": "#cf2e27",
                       "src": "",
                       "link": "",
                       "link-dsk": ""
                   }
               ],
               "mortgages": [
                   0,
                   1,
                   2,
                   4,
                   5,
                   6,
                   7,
                   8
               ],
               "uuid": "f64ad1a2-d11c-4726-a23e-ac9ce35dff39",
               "type_flat": 1,
               "group-flat": false,
               "decoration": [
                   0,
                   10,
                   30,
                   70
               ],
               "vdsk": false,
               "decorationTitle": "<p><span style=\"color:rgb(13, 10, 48)\">Вы можете приобрести квартиру без отделки, White Box или в одной&nbsp;из 4 готовых концепций дизайна интерьера</span></p>\r\n",
               "decorationDescription": "",
               "launch_announcement": false,
               "logo": "",
               "sites": [
                   "dsk"
               ],
               "hide_on_index": false,
               "finishconf": false,
               "flatFeatures": [
                   "000000006",
                   "000000009",
                   "000000013",
                   "000000015",
                   "000000016",
                   "000000027",
                   "000000036",
                   "000000050",
                   "000000052",
                   "000000056",
                   "000000093",
                   "000000094"
               ],
               "sort": 21
           },
           {
               "id": 29,
               "slug": "centr-2",
               "title": "Центр-2",
               "class": 2,
               "propertyClass": 2,
               "flatType": 0,
               "min_area": null,
               "max_area": null,
               "is_active": null,
               "file_id": 369569,
               "file": {
                   "id": 369569,
                   "file_name": "61697a4004f71_bed3a120ac5dcd8667f6b4129df7ea6e.png",
                   "is_img": true,
                   "title": null,
                   "description": null,
                   "sort": -1,
                   "created_at": "2018-12-03 19:14:58",
                   "updated_at": "2021-10-15 12:55:27",
                   "type": "image/png",
                   "original_name": "центр-2.png",
                   "bucket": "fsk-uploads"
               },
               "header_color": null,
               "hover_color": null,
               "cost_from": "",
               "lat": 55.742242181108,
               "lng": 38.003818410157,
               "city_id": 1,
               "new_view": false,
               "post_address": "Московская обл., г. Балашиха, мкр. Железнодорожный, ул. Корнилаева",
               "img": "https://fsk-uploads.hb.bizmrg.com/production/61697a4004f71_bed3a120ac5dcd8667f6b4129df7ea6e.png",
               "img_single": "https://fsk-uploads.hb.bizmrg.com/production/61697a40305a4_bed3a120ac5dcd8667f6b4129df7ea6e.png",
               "img_double": "https://fsk-uploads.hb.bizmrg.com/production/62d95682d72df_d95fc966b88875b6eeefe1fd1d27fa80.jpg",
               "tile_size": 1,
               "transport": {
                   "from": "Железнодорожная",
                   "color": "#303337",
                   "route": [
                       {
                           "type": "foot",
                           "time": 15
                       }
                   ]
               },
               "offer": 0,
               "flats": {
                   "built": 0,
                   "furniture": 0,
                   "finish": 1,
                   "booking": 1,
                   "all": 1
               },
               "flatsHasSalePrice": false,
               "minPriceBuilt": 0,
               "price_from": 12096000,
               "price_to": 12096000,
               "area_from": 89,
               "area_to": 89,
               "build_to": "2019-12-30",
               "build_from": "2019-11-25",
               "build": [
                   "2019-11-25",
                   "2019-12-10",
                   "2019-12-25",
                   "2019-12-30",
                   null
               ],
               "sidebar": {
                   "flats": true,
                   "parking": false,
                   "pantry": false,
                   "map": true,
                   "decoration": true,
                   "furniture": false,
                   "smart": false,
                   "progress": true,
                   "document": true,
                   "promo": false,
                   "mortgage": true,
                   "news": true,
                   "contacts": true,
                   "apartments": false,
                   "apart-hotel": false,
                   "installment_plan": true,
                   "netting": true,
                   "military_mortgage": false,
                   "maternal_capital": true,
                   "subsidies": false,
                   "evropeiskaya": true
               },
               "rooms": [
                   3
               ],
               "dsk": true,
               "labels": [
                   {
                       "title": "Все дома сданы",
                       "color": "#cf2e27",
                       "src": "",
                       "link": "",
                       "link-dsk": ""
                   }
               ],
               "mortgages": [
                   0,
                   2
               ],
               "uuid": "69fba97b-8cd8-4519-bb74-661d96f1f3e3",
               "type_flat": 1,
               "group-flat": false,
               "decoration": [
                   10
               ],
               "vdsk": false,
               "decorationTitle": "",
               "decorationDescription": "",
               "launch_announcement": false,
               "logo": "",
               "sites": [
                   "dsk"
               ],
               "hide_on_index": false,
               "finishconf": false,
               "flatFeatures": [],
               "sort": 22
           }
       ]
       """.trimIndent()

    val listOfComplex =
        Json.decodeFromString<List<DskResponceItem>>(data)
}