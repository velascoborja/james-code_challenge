package com.example.james_code_challenge.mock

import com.example.james_code_challenge.data.model.Card
import com.example.james_code_challenge.data.model.Icon
import com.example.james_code_challenge.data.model.Phase
import com.example.james_code_challenge.data.model.Procedure
import com.example.james_code_challenge.data.model.ProcedureDetail

class MockData {
    companion object {
        val procedureMock = Procedure(
            uuid = "procedure-SIU_HandTrans",
            icon = Icon(
                uuid = "ICON-11986",
                url = "https://content.touchsurgery.com/16/d7/16d7e3da0b572e79d445992ce450b36608167c29544d32811d5c46dac43fc527?Expires=1724151613&Signature=M2zfe6zR24IjW5JcOZsy0ZGIAVQ-XBZohhZZwXIDmOpR4QhSS5SM9VRbQ5iROlik79lKB73~a6Ol5sk4az16RE2j4aEWnBao1zFfwCaPtofu-~1JqO9LvjTHHem5X19-EIwhwmGQR03f7k2iO0PWwLDuacNMQ5-UZ9d6ip-VxysLuxKd-YQxJ8IQEB4kEM~ymM2GR8e8IrCJbzQZf5Av2HxR1zwTCJixjOAzLS9ZN3dJ~YuYGhwQlkcR1m77AjSiQXWjrmBFN-YQ-cWWorjUFnJH9G9AnHyLWvepiP3PiksS5EpeFYNhhNmTehuRLBBqsVDcW1k9~MUouOr6OX7pvA__&Key-Pair-Id=APKAISEZV3CS3QKJL3CQ",
                version = 100
            ),
            name = "Hand Transplant Donor Preparation",
            phases = listOf("module-SIU_HandTrans_010"),
            specialties = listOf(""),
            deepLink = "https://lh68.app.link/?deeplink_path=library/procedure?procedure_code=procedure-SIU_HandTrans&~tags=SIU_HandTrans&desktop_passive_deepview=touch_surgery_deepview_web",
            author = "Michael W. Neumeister",
            "Shaun D. Mendenhall",
            doiCode = "",
            datePublished = "2015-06-04T17:13:48.440",
            labels = listOf(""),
            siteSlug = "hand-transplant",
            duration = 4651,
            isPurchasable = false
        )

        val procedureDetailMock = ProcedureDetail(
            uuid = "uuid",
            name = "name",
            phases = listOf(
                Phase(
                    "", "", Icon(
                        uuid = "ICON-11986",
                        url = "https://content.touchsurgery.com/16/d7/16d7e3da0b572e79d445992ce450b36608167c29544d32811d5c46dac43fc527?Expires=1724151613&Signature=M2zfe6zR24IjW5JcOZsy0ZGIAVQ-XBZohhZZwXIDmOpR4QhSS5SM9VRbQ5iROlik79lKB73~a6Ol5sk4az16RE2j4aEWnBao1zFfwCaPtofu-~1JqO9LvjTHHem5X19-EIwhwmGQR03f7k2iO0PWwLDuacNMQ5-UZ9d6ip-VxysLuxKd-YQxJ8IQEB4kEM~ymM2GR8e8IrCJbzQZf5Av2HxR1zwTCJixjOAzLS9ZN3dJ~YuYGhwQlkcR1m77AjSiQXWjrmBFN-YQ-cWWorjUFnJH9G9AnHyLWvepiP3PiksS5EpeFYNhhNmTehuRLBBqsVDcW1k9~MUouOr6OX7pvA__&Key-Pair-Id=APKAISEZV3CS3QKJL3CQ",
                        version = 100
                    )
                ),
                Phase(
                    "", "", Icon(
                        uuid = "ICON-11986",
                        url = "https://content.touchsurgery.com/16/d7/16d7e3da0b572e79d445992ce450b36608167c29544d32811d5c46dac43fc527?Expires=1724151613&Signature=M2zfe6zR24IjW5JcOZsy0ZGIAVQ-XBZohhZZwXIDmOpR4QhSS5SM9VRbQ5iROlik79lKB73~a6Ol5sk4az16RE2j4aEWnBao1zFfwCaPtofu-~1JqO9LvjTHHem5X19-EIwhwmGQR03f7k2iO0PWwLDuacNMQ5-UZ9d6ip-VxysLuxKd-YQxJ8IQEB4kEM~ymM2GR8e8IrCJbzQZf5Av2HxR1zwTCJixjOAzLS9ZN3dJ~YuYGhwQlkcR1m77AjSiQXWjrmBFN-YQ-cWWorjUFnJH9G9AnHyLWvepiP3PiksS5EpeFYNhhNmTehuRLBBqsVDcW1k9~MUouOr6OX7pvA__&Key-Pair-Id=APKAISEZV3CS3QKJL3CQ",
                        version = 100
                    )
                ),
                Phase(
                    "", "", Icon(
                        uuid = "ICON-11986",
                        url = "https://content.touchsurgery.com/16/d7/16d7e3da0b572e79d445992ce450b36608167c29544d32811d5c46dac43fc527?Expires=1724151613&Signature=M2zfe6zR24IjW5JcOZsy0ZGIAVQ-XBZohhZZwXIDmOpR4QhSS5SM9VRbQ5iROlik79lKB73~a6Ol5sk4az16RE2j4aEWnBao1zFfwCaPtofu-~1JqO9LvjTHHem5X19-EIwhwmGQR03f7k2iO0PWwLDuacNMQ5-UZ9d6ip-VxysLuxKd-YQxJ8IQEB4kEM~ymM2GR8e8IrCJbzQZf5Av2HxR1zwTCJixjOAzLS9ZN3dJ~YuYGhwQlkcR1m77AjSiQXWjrmBFN-YQ-cWWorjUFnJH9G9AnHyLWvepiP3PiksS5EpeFYNhhNmTehuRLBBqsVDcW1k9~MUouOr6OX7pvA__&Key-Pair-Id=APKAISEZV3CS3QKJL3CQ",
                        version = 100
                    )
                ),
                Phase(
                    "", "lastPhase", Icon(
                        uuid = "ICON-11986",
                        url = "https://content.touchsurgery.com/16/d7/16d7e3da0b572e79d445992ce450b36608167c29544d32811d5c46dac43fc527?Expires=1724151613&Signature=M2zfe6zR24IjW5JcOZsy0ZGIAVQ-XBZohhZZwXIDmOpR4QhSS5SM9VRbQ5iROlik79lKB73~a6Ol5sk4az16RE2j4aEWnBao1zFfwCaPtofu-~1JqO9LvjTHHem5X19-EIwhwmGQR03f7k2iO0PWwLDuacNMQ5-UZ9d6ip-VxysLuxKd-YQxJ8IQEB4kEM~ymM2GR8e8IrCJbzQZf5Av2HxR1zwTCJixjOAzLS9ZN3dJ~YuYGhwQlkcR1m77AjSiQXWjrmBFN-YQ-cWWorjUFnJH9G9AnHyLWvepiP3PiksS5EpeFYNhhNmTehuRLBBqsVDcW1k9~MUouOr6OX7pvA__&Key-Pair-Id=APKAISEZV3CS3QKJL3CQ",
                        version = 100
                    )
                )
            ),
            icon = Icon("", "", 99),
            card = Card("", "", 99),
            "2015-06-04T17:13:48.440",
            999
        )
    }
}